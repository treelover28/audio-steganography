package audioSteganography;

import java.io.File;
import java.util.Scanner;


public class SteganographyDriver {
	public static void main(String[] args) 
	{
		try
		{
			System.out.println("Do you wish to encode or decode an audio file? Type \'encode\' or \'decode\' to continue. \nElse, type \'q\' to quit.");
			Scanner keyboard = new Scanner(System.in);
			String choice = keyboard.next();
			while (!choice.equalsIgnoreCase("encode") || !choice.equalsIgnoreCase("decode") || !choice.equals("q") )
			{
				
				if  (choice.equalsIgnoreCase("encode"))
				{
					System.out.println("Please type in input audio file's name: ");
					String fileName = keyboard.next();
					File inputFile = new File(fileName);
					System.out.println("Please name the encrypted file (one consecutive word):  ");
					String outputName = keyboard.next();
					File outputFile =new File(outputName);
					
					if (!inputFile.exists())
					{
						keyboard.close();
						throw new SecretMessageException("SecretMessageException: File is not found");
						
					}
					
					System.out.println("What hidden message do you wish to hide in the encrypted file?");
					keyboard.nextLine(); // flush keyboard 
					String message = keyboard.nextLine();
				
					Steganography.encodeMessage(inputFile, outputFile, message);
					if (message.getBytes().length > inputFile.length())
					{
						keyboard.close();
						throw new NotEnoughSpaceException(message.getBytes().length, inputFile.length());
					}
		
					System.out.println("If you wish to quit, type 'q' now. Else, Type \'encode\' or \'decode\' to continue");
					choice = keyboard.next();
					if (choice.equals("q"))
					{
						System.out.println("Program has terminated.");
						keyboard.close();
						break;
					}
					
				}
				else if (choice.equalsIgnoreCase("decode"))
				{
					System.out.println("Please type in input audio file's name: ");
					String fileName = keyboard.next();
					File inputFile = new File(fileName);
					String secretMessage = Steganography.decodeMessage(inputFile);
					System.out.println("Message: " + secretMessage ); 
					if ((secretMessage.equals("") || secretMessage.equals(null)))
					{
						keyboard.close();
						throw new SecretMessageException("SecretMessageException: Secret Message is not found");
					}
					
					System.out.println("If you wish to quit, type 'q' now. Else, Type \\'encode\\' or \\'decode\\'.");
					choice = keyboard.next();
					if (choice.equals("q"))
					{
						System.out.println("Program has terminated.");
						keyboard.close();
						break;
					}
				}
				else if (choice.equals("q"))
				{
					System.out.println("Program has terminated.");
					keyboard.close();
					break;
				}
				else
				{
					System.out.println("Unable to recognize command. Type \'encode\' or \'decode\' or \'q' to quit'");
					choice = keyboard.next();
					if (choice.equals("q"))
					{
						System.out.println("Program has terminated.");
						keyboard.close();
						break;
					}
				}	
			}
		}
		catch (SecretMessageException e)
		{
			System.out.println(e.getMessage());
		}
		catch (NotEnoughSpaceException e)
		{
			System.out.println(e.getMessage());
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.getMessage());
		}		
	}
}
