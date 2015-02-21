package common;

import common.Predicate;

public class Client {
	/* Given the array of command line arguments, this function will
	   return an object of the class in predicates/CLASSNAME.class, where
	   CLASSNAME is the value in argv[1]. If argv[2] exists, it will be
	   passed to the CLASSNAME constructor as a String.  If it does not,
	   the default constructor will be used.

	   e.g. if argv[] is { "Client", "Foo", "bar" }, this will attempt
	   to load predicates/Foo.class and return new Foo("bar");
	   If argv[] = { "Client", "Foo" }, it will instead return new Foo();
	 */
	public static Predicate getPredicate(String argv[]) {
		try {
			Class c = Class.forName("predicates." + argv[1]);
			return (3 == argv.length)
				?  (Predicate)c.getConstructor(String.class).newInstance(argv[2])
				:  (Predicate)c.newInstance();
		}
		catch (Exception e) {
			System.err.println(String.format("%s\n  when attempting to load predicates.%s.class\n", 
				e.toString(), argv[1]));
			System.exit(1);
		}

		return null;//can't happen
	}

	public static void main(String argv[]) {
		if (2 != argv.length && 3 != argv.length) {
			System.err.println(String.format("%s\n%s\n%s\n%s",
				"Usage: java Client <host> <classname> <ctor arg (optional)>",
				"<host> is the location of the RMI server",
				"<classname> is the name of a class in package predicates that implements Predicate, Serializable",
				"<ctor arg>, if present, will be passed to <classname>'s constructor as a string."));
			System.exit(1);
		}

		Predicate pred = getPredicate(argv);
		/* pred now points to an object of the type specified by the command line arguments. */

	}    
}

