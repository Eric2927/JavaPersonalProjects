import java.io.*;
import java.util.*;

public class IMDBGraphImpl implements IMDBGraph {
	
	// Stores actors
	private class ActorNode implements Node {
		
		private String _actorName;
		private Collection<MovieNode> _movies;
		
		public ActorNode(String name) {
			_actorName = name;
			_movies = new HashSet<MovieNode>();
		}

		public String getName() {
			return _actorName;
		}

		public Collection<? extends Node> getNeighbors() {
			return _movies;
		}
		
		// Adds a movie to the list of movies that this actor has played in
		public void addMovie(MovieNode movie) {
			_movies.add(movie);
		}
		
		// Returns true if the actor has played in a movie
		public boolean hasMovies() {
			return !_movies.isEmpty();
		}

	}
	
	// Stores movies
	private class MovieNode implements Node {
		
		private String _movieName;
		private Collection<ActorNode> _actors;
		
		public MovieNode(String name) {
			_movieName = name;
			_actors = new HashSet<ActorNode>();
		}

		public String getName() {
			return _movieName;
		}

		public Collection<? extends Node> getNeighbors() {
			return _actors;
		}
		
		// Adds an actor to the list of actors who played in this movie
		public void addActor(ActorNode actor) {
			_actors.add(actor);
		}

	}
	
	// Stores graph of actor and movie nodes. Key is the name of movie/actor, the value is the node.
	private HashMap<String, ActorNode> _actorNodes;
	private HashMap<String, MovieNode> _movieNodes;
	
	/**
	 * Constructs a graph of actors and actresses and the movies they played in
	 * @param actorFile file containing list of actors
	 * @param actressFile file containing list of actresses
	 * @throws IOException When file is incorrectly formated (not in IMDB format)
	 */
	public IMDBGraphImpl(String actorFile, String actressFile) throws IOException {
		final Scanner inFileActor = new Scanner(new File(actorFile), "ISO-8859-1"); // Reads actor file
		final Scanner inFileActress = new Scanner(new File(actressFile), "ISO-8859-1"); // Reads actress file
		// Initializes graph
		_actorNodes = new HashMap<String, ActorNode>();
		_movieNodes = new HashMap<String, MovieNode>();
		// Parses the files into graph
		parseHeader(inFileActor, true);
		parseHeader(inFileActress, false);
	}
	
	/**
	 * Locates the beginning of the list and removes everything beforehand
	 * @param file the IMDB file being parsed
	 * @param male true if the file is the actor file; false if the file is actress file
	 * @throws IOException when file is incorrectly formatted (not in IMDB format)
	 */
	private void parseHeader(Scanner file, boolean male) throws IOException {
		String fileListHeader;
		// Line counter was used for debugging purposes.
		// Integer lineCounter = 1;
		if (male) {
			fileListHeader = "THE ACTORS LIST";
		}
		else {
			fileListHeader = "THE ACTRESSES LIST";
		}
		// Keeps going through the file until it reaches the header of the list of actors/actresses
		while (file.hasNextLine()) {
			if (file.nextLine().equals(fileListHeader)) {
				break;
			}
			// lineCounter ++;
		}
		// Throws exception if file does not have the appropriate IMDB format
		if (!file.hasNextLine()) {
			throw new IOException("File not properly formatted.");
		}
		// Ignores the next four lines after the header (irrelevant information)
		for (int i = 0; i < 4; i ++) {
			file.nextLine();
			// lineCounter ++;
		}
		
		parseFile(file /*, lineCounter*/);
	}

	/**
	 * Parses the list of actors/actresses and movies into nodes in the graph
	 * @param file the file being parsed
	 * @throws IOException when file is incorrectly formatted (not in IMDB format)
	 */
	private void parseFile(Scanner file /*, Integer lineCounter*/) throws IOException {
		String thisLine1 = file.nextLine();
		// lineCounter ++;
		// Iterates through every actor until it reaches the end of the list
		while (!thisLine1.equals("-----------------------------------------------------------------------------")) {
			String actorName = thisLine1.substring(0, thisLine1.indexOf('\t'));
			final ActorNode actor = new ActorNode(actorName);
			// Parses the movies of the actor being currently processed
			parseMovies(file, actor, thisLine1.substring(thisLine1.indexOf('\t')).trim() /*, lineCounter*/);
			// Ignores the actor if the actor has not performed in any valid movies (aka non-TV)
			if (actor.hasMovies()) {
				_actorNodes.put(actorName, actor);
			}
			// Keeps going through the file is possible
			if (file.hasNextLine()) {
				thisLine1 = file.nextLine();
				// lineCounter ++;
			}
			else {
				break;
			}
		}
	}
	
	/**
	 * Returns true if the movie is not a TV movie/series, false otherwise
	 * @param line the rest of the line after the movie name in the file
	 * @param movieName the name of the movie
	 * @return true if movie is not TV movie/series, false otherwise
	 */
	private boolean isValidMovie(Scanner line, String movieName) {
		boolean isValidMovie = true; // Boolean that represents whether movie is valid (aka not a TV movie or TV series)
		// Checks if movie is a TV series. Note that it checks last character of movie title
		// by looking at the character at movieName.length() - 2 because there is a extra space at the
		// end of movieName (check line 128)
		if (movieName.charAt(0) == '"' && movieName.charAt(movieName.length() - 2) == '"') {
			isValidMovie = false;
		}
		// Checks if movie is a TV movie
		if (line.hasNext() && line.next().equals("(TV)")) {
			isValidMovie = false;
		}
		return isValidMovie;
	}
	
	/**
	 * Parses the movies into nodes
	 * @param file the file being parsed
	 * @param actor the node of the actor
	 * @param line the line currently being parsed
	 */
	private void parseMovies(Scanner file, ActorNode actor, String line /*, Integer lineCounter */) {
		// Keeps looping through the file until it gets to an empty line (next actor is always listed after an empty line)
		while (!line.isEmpty()) {
			Scanner lineScanner = new Scanner(line);
			String movieName = "";
			String temp = lineScanner.next();
			boolean partOfTitle = true; // Excluding year
			// Iterates through the whole movie title and descriptions until it finds the year
			while (partOfTitle) {
				movieName = movieName + temp + " "; // Adds next word in the line to movieName
				temp = lineScanner.next();
				// Checks if the word stored in temp is the year of movie production
				if (temp.charAt(0) == '(' && temp.charAt(temp.length() - 1) == ')') {
					if (temp.length() == 6) {
						partOfTitle = false;
					}
					else if (temp.length() > 6) {
						boolean isYear = true;
						try {
							Integer.valueOf(temp.substring(1, 5));
						}
						catch (Exception e) {
							if (!temp.substring(1,5).equals("????")) {
								isYear = false;
							}
						}
						partOfTitle = !isYear;
					}
				}
			}
			if (isValidMovie(lineScanner, movieName)) {
				movieName = movieName + temp; // the year of production is added to the movie name
				// Checks if the movie already exists in the graph
				if (!_movieNodes.containsKey(movieName)) {
					final MovieNode movie = new MovieNode(movieName);
					_movieNodes.put(movieName, movie); // Adds movie to graph if movie is valid and movie doesn't already exist
				}
				actor.addMovie(_movieNodes.get(movieName)); // Puts the movie into the actor's list of movies 
				_movieNodes.get(movieName).addActor(actor); // Adds the actor into the movie's list of actors
			}
			lineScanner.close();
			// If file has not ended (file should never end here if file is formatted correctly, but just in case), keep going
			if (file.hasNextLine()) {
				line = file.nextLine(); // proceeds to next line (aka movie or empty line)
				// lineCounter ++;
			}
			else {
				break;
			}
		}
	}

	public Collection<? extends Node> getActors() {
		return _actorNodes.values();
	}

	public Collection<? extends Node> getMovies() {
		return _movieNodes.values();
	}

	public Node getMovie(String name) {
		return _movieNodes.get(name);
	}

	public Node getActor(String name) {
		return _actorNodes.get(name);
	}
	
	
}
