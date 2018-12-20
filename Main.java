/*
Complete your details...
Name and Surname: Mohamed Ameen Omar
Student/staff Number: Mohamed Ameen Omar
*/


public class Main
{
	/*Place your main and your test code here*/

	public static void main(String[] args)
	{
		DigitalPatriciaTree tree = new DigitalPatriciaTree();

		System.out.println("\n-------------Begin Inserting---------------------------\n");
		int index = 0;
		int q = 0;
		while(index <20)
		{
			for(int x =65 ; x<91 ; x++)
			{
				char ch = (char)x;
				String output = "";

				for(int y =0; y<q+1;y++)
				{
					output+=Character.toString(ch);

				}
				System.out.println("Inserting " + output);
				System.out.println(tree.insert(output));
				System.out.println(tree.search(output));
			}

			index++;
			q++;
		}

		index = 0;
		q = 0;
		while(index <20)
		{
			for(int x =65 ; x<91 ; x++)
			{
				char ch = (char)x;
				String output = "";

				for(int y =0; y<q+1;y++)
				{
					output+=Character.toString(ch);

				}
				System.out.println("Inserting " + output);
				System.out.println(tree.insert(output));
				System.out.println(tree.search(output));
			}

			index++;
			q++;
		}

		index = 0;
		q = 0;
		while(index <20)
		{
			for(int x =90 ; x>64 ; x--)
			{
				char ch = (char)x;
				String output = "";

				for(int y =0; y<q+1;y++)
				{
					output+=Character.toString(ch);

				}
				System.out.println("Inserting " + output);
				System.out.println(tree.insert(output));
				System.out.println(tree.search(output));
			}

			index++;
			q++;
		}
	}
}
