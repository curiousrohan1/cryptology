import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random randgen = new Random(System.currentTimeMillis());
        int mood = randgen.nextInt(4);
        String[] responses1 = {
                "Oh, hey. Wanna use a cipher? If you don't, that's okay too. I mean, it would be great if you used a cipher. I'm very lonely, and - \n\nHuh? You'll use me-I mean it? Oh, that's great! In that case, do you want to encrypt or decrypt?",
                "Hello. Wouldst it perchance please you to use a cipher? It is quite valuable to be able to decrypt and encrypt messages. Such ability helps with secret communication, after all. \n\nAh! you do! Which one? Encrypt or decrypt?",
                "Hey there! Welcome to... this cipher program! Wanna use it? Oh, of course you do! Why would you run this program if you don't want to use it? Anyway, do you want to encrypt or decrypt?",
                "Oy! Stupid! Use this cipher program! Or you'll find yourself in indeterminate amounts of pain...\n\nWhat do you mean, I'm not nice?! I'm a freaking treat! Now, do you want to encrypt or decrypt? Before I lose my patience!" };
        String[] responses2encrypt = {
                "Alright. Be forewarned, I suck at this - and everything else. Well then, let's get started. Oh, I almost forgot! Which cipher do you want to use to encrypt?",
                "Ah, encrypting. Excellent choice. Which cipher would it please sir and madame to use?",
                "Okay, then! Which cipher do you wanna use? You know what? I'll choose for you!\n\nOh, you have a cipher in mind? Alright, what is it?",
                "Encrypting?! What secrets are you trying to hide?\n\n\"None of your business\"? Everything's my business, fool! Anyway, what cipher you wanna use?" };
        String[] responses2decrypt = {
                "Ooh! I'm actually good at decrypting! It's the only thing I'm good at. God, I hate myself. Anyway, what's the cipher you want to use?",
                "Ah, decrypting. Excellent choice. Which cipher would it please sir and/or madame to use?",
                "Decrypting! Sounds good! Which cipher?",
                "Decrypting, huh? You've intercepted secret communications and now you need the help of a machine of greater intellect than you to decrypt it and expose the secrets of your enemies!\n\nOh, an encrypted letter from a friend and you're too lazy to decrypt by hand... I didn't expect a peon like you to have friends! I suppose there's hope for all of us in that case! Anyway, what cipher did your (no doubt stupid)friend use?" };
        String[] responses3 = { "That cipher? Alright...", "Ah, an excellent choice. My personal favorite.",
                "Awesome! Ready for the next step?", "Wow. That's such a stupid cipher. For a stupid person." };
        String[] responses4 = { "All done. Goodbye, I guess...",
                "It is done, good sir and/or madame. Come again soon!\n\nWhat? No compensation is needed, milord and/or milady.",
                "Done! See you later!", "There's your stupid text. Now leave me alone." };
        System.out.println(responses1[mood]);
        String encrypt = input.nextLine().toUpperCase();
        boolean encrypting = encrypt.equals("ENCRYPT");
        String msg = "";
        if (encrypting) {
            System.out.println(responses2encrypt[mood]);
            String cipher = input.nextLine().toUpperCase();
            System.out.println(responses3[mood] + " What is the message you want to encrypt?");
            String pt = input.nextLine().toUpperCase();
            switch (cipher) {
                case "AFFINE": {
                    System.out.println(
                            "What's the key[a number to multiply by and a number to add by in the format: (mult, add)]");
                    String key = input.nextLine();
                    int mult = Integer.parseInt(key.substring(1, key.indexOf(',')));
                    int add = Integer.parseInt(key.substring(key.indexOf(' ') + 1, key.indexOf(')')));
                    msg = Affine.encrypt(pt, mult, add);
                    break;
                }
                case "ATBASH": {
                    msg = Atbash.encrypt(pt);
                    break;
                }
                case "BAUDOT": {
                    System.out.println("What's the seed?");
                    String seed = input.nextLine();
                    System.out
                            .println("What's the rule?(two numbers, less than 5, that are used to generate the seed).");
                    String rule = input.nextLine();
                    msg = Baudot.encrypt(pt, seed, rule);
                    break;
                }
                case "CAESAR": {
                    System.out.println("What is the shift letter?");
                    char shiftLetter = input.nextLine().charAt(0);
                    msg = Caesar.encrypt(pt, shiftLetter);
                    break;
                }
                case "BLOCK": {
                    System.out.println("What is the key?(Two letters)");
                    String key = input.nextLine();
                    msg = DES.encrypt(pt, key);
                    break;
                }
                case "HILL": {
                    System.out.println("What is the key?(4 letters)");
                    String key = input.nextLine();
                    msg = Hill.encrypt(pt, key);
                    break;
                }
                case "MONOALPH": {
                    System.out.println(
                            "Do you want to use a keyword and keyletter(0) or enter your own randomized alphabet(1)?");
                    int rep = input.nextInt();
                    if (rep == 0) {
                        System.out.println("What is the keyword and keyletter?(kw, kl)");
                        String keyword = input.nextLine();
                        char keyletter = keyword.charAt(keyword.indexOf(' ') + 1);
                        keyword = keyword.substring(1, keyword.indexOf(','));
                        msg = Monoalph.encrypt(pt, Monoalph.getAlph(keyword, keyletter));
                    } else {
                        System.out.println(
                                "What is the alphabet?(List them with no commas or spaces separating the letters)");
                        char[] alph = new char[26];
                        String bet = input.nextLine();
                        for (int i = 0; i < alph.length; i++) {
                            alph[i] = bet.charAt(i);
                        }
                        msg = Monoalph.encrypt(pt, alph);
                    }
                    break;
                }
                case "PLAYFAIR": {
                    System.out.println("What is the keyword?(5 letters)");
                    String kw = input.nextLine();
                    msg = Playfair.encrypt(pt, kw);
                    break;
                }
                case "RAILFENCE": {
                    System.out.println("How many rails?");
                    int rails = input.nextInt();
                    msg = RailFence.encrypt(pt, rails);
                    break;
                }
                case "VIGENERE": {
                    System.out.println("What is the keyword?");
                    String keyword = input.nextLine();
                    msg = Vigenere.encrypt(pt, keyword);
                    break;
                }
            }
        } else {
            System.out.println(responses2decrypt[mood]);
            String cipher = input.nextLine().toUpperCase();
            System.out.println(responses3[mood] + " What is the ciphertext you want to decrypt?");
            String ct = input.nextLine().toUpperCase();
            switch (cipher) {
                case "AFFINE": {
                    System.out.println(
                            "What's the key[a number to multiply by and a number to add by in the format: (mult, add)]");
                    String key = input.nextLine();
                    int mult = Integer.parseInt(key.substring(1, key.indexOf(',')));
                    int add = Integer.parseInt(key.substring(key.indexOf(' ') + 1, key.indexOf(')')));
                    msg = Affine.decrypt(ct, mult, add);
                    break;
                }
                case "ATBASH": {
                    msg = Atbash.decrypt(ct);
                    break;
                }
                case "BAUDOT": {
                    System.out.println("What's the seed?");
                    String seed = input.nextLine();
                    System.out
                            .println("What's the rule?(two numbers, less than 5, that are used to generate the seed).");
                    String rule = input.nextLine();
                    msg = Baudot.decrypt(ct, seed, rule);
                    break;
                }
                case "CAESAR": {
                    System.out.println("What is the shift letter?");
                    char shiftLetter = input.nextLine().charAt(0);
                    msg = Caesar.decrypt(ct, shiftLetter);
                    break;
                }
                case "BLOCK": {
                    System.out.println("What is the key?(Two letters)");
                    String key = input.nextLine();
                    msg = DES.decrypt(ct, key);
                    break;
                }
                case "HILL": {
                    System.out.println("What is the key?(4 letters)");
                    String key = input.nextLine();
                    msg = Hill.decrypt(ct, key);
                    break;
                }
                case "MONOALPH": {
                    System.out.println(
                            "Do you want to use a keyword and keyletter(0) or enter your own randomized alphabet(1)?");
                    int rep = input.nextInt();
                    if (rep == 0) {
                        System.out.println("What is the keyword and keyletter?(kw, kl)");
                        String keyword = input.nextLine();
                        char keyletter = keyword.charAt(keyword.indexOf(' ') + 1);
                        keyword = keyword.substring(1, keyword.indexOf(','));
                        msg = Monoalph.decrypt(ct, Monoalph.getAlph(keyword, keyletter));
                    } else {
                        System.out.println(
                                "What is the alphabet?(List them with no commas or spaces separating the letters)");
                        char[] alph = new char[26];
                        String bet = input.nextLine();
                        for (int i = 0; i < alph.length; i++) {
                            alph[i] = bet.charAt(i);
                        }
                        msg = Monoalph.decrypt(ct, alph);
                    }
                    break;
                }
                case "PLAYFAIR": {
                    System.out.println("What is the keyword?(5 letters)");
                    String kw = input.nextLine();
                    msg = Playfair.decrypt(ct, kw);
                    break;
                }
                case "RAILFENCE": {
                    System.out.println("Ha. No.");
                    msg = "That doesn't exist, sorry.";
                    break;
                }
                case "VIGENERE": {
                    System.out.println("What is the keyword?");
                    String keyword = input.nextLine();
                    msg = Vigenere.decrypt(ct, keyword);
                    break;
                }
            }
        }
        input.close();
        System.out.println(msg);
        System.out.println(responses4[mood]);
    }
}
