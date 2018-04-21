public class Fibonacci {

  public static void main(String[] args) {

  	if (args.length <= 0) {
  		System.out.println("The Fibonacci Class can not function without a value! ");
  	}

  	BrobInt a = new BrobInt();
  	BrobInt b = new BrobInt();
  	BrobInt c = new BrobInt();

    int input = Integer.parseInt(args[0]);

    System.out.println("Commencing sequence...");

    if (args.length == 1) {

      if (input == 0) {

        System.out.println(String.format("Result:\n %d", input));
        return;

      } else if (input <= 2) {

        System.out.println(String.format("Result:\n %d", 1));
        return;

      } else {

        a = new BrobInt("1");
  			b = new BrobInt("2");

        for (int i = 3; i < input; i++) {
          c = new BrobInt(a.add(b).toString());
  				a = new BrobInt(b.toString());
  				b = new BrobInt(c.toString());
        }

        System.out.println(b.toString());

      }

    }

  }

}
