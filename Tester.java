public class Tester
{
	public static void main(String[] args)
	{
		String word = "I";
		String binary = stringToBinary(word);

		System.out.println("Converting " + word);

		System.out.println("Binary Version: " + binary);

		System.out.println("String Version: " + binaryToString(binary).equals(word));
	}

	/*

				Binary Conversions

	*/

	//Takes a String parameter and returns the binary equivalent of the string
	public static String stringToBinary(String temp)
	{
		int count = 0;

		String binary;

		binary = charToBinary(temp.charAt(count));

		count++;

		while(count < temp.length())
		{
			binary += charToBinary(temp.charAt(count));
			count++;
		}

		return binary;

	}//WORKS


	//takes a character parameter and returns the binary equaivalent of the character
	public static String charToBinary(char temp)
	{
		int value = ((int)temp) - 65; //convert to ascii then get code, 'A' = 0

		String binaryString = Integer.toBinaryString(value);

		//convert to binary consisting of 5 bits
		while (binaryString.length() < 5)
		{
			 binaryString = "0" + binaryString;
		}
		return binaryString;

	}//WORKS

	//takes in a Binary string and returns the character string equivalent
	public static String binaryToString(String arg)
	{
		String temp = "";

		int count = 0;

		String output = "";

		while(count < arg.length())
		{
			temp = arg.substring(count, count+5);//create substring
			output += binaryToChar(temp);//convert to char and add to the string
			count +=5;
		}

		return output;
	}//WORKS

	//takes in a binary string and returns the character equivalent as a String
	public static String binaryToChar(String arg)
	{
		int asciiInt = Integer.parseInt(arg, 2);//convert from binary to decimals

		char character = (char)(asciiInt + 65); //convert to actual ASCII and convert to char

		return Character.toString(character);
	}//WORKS
}
