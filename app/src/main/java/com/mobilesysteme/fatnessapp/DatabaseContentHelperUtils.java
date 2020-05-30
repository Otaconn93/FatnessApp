package com.mobilesysteme.fatnessapp;

public abstract class DatabaseContentHelperUtils {

    public static void fillDatabase(DatabaseHelper databaseHelper) {

        databaseHelper.addUnit("Milligram", "mg");
        databaseHelper.addUnit("Milliliter", "ml");

        databaseHelper.addFoodGroup(-1, "Obst und Gemüse");                                 // 1
        databaseHelper.addFoodGroup(-1, "Fleisch, Fisch und Fleischersatz");
        databaseHelper.addFoodGroup(-1, "Milchprodukte, -alternativen und Ei");
        databaseHelper.addFoodGroup(-1, "Backwaren und Getreideprodukte");
        databaseHelper.addFoodGroup(-1, "Hülsenfrüchte");
        databaseHelper.addFoodGroup(-1, "Getränke");
        databaseHelper.addFoodGroup(-1, "Süßigkeiten und Fast Food");
        databaseHelper.addFoodGroup(-1, "Sonstiges");

        databaseHelper.addFoodGroup(1, "Obst");                                             // 9
        databaseHelper.addFoodGroup(1, "Gemüse");

        databaseHelper.addFoodGroup(2, "Fleisch");                                          // 11
        databaseHelper.addFoodGroup(2, "Fisch");
        databaseHelper.addFoodGroup(2, "Fleischersatz");

        databaseHelper.addFoodGroup(4, "Backwaren");                                        // 14
        databaseHelper.addFoodGroup(4, "Getreideprodukte");
        databaseHelper.addFoodGroup(4, "Kuchen");

        databaseHelper.addFoodGroup(6, "Kaffee & Tee");                                     // 17
        databaseHelper.addFoodGroup(6, "Softdrinks");
        databaseHelper.addFoodGroup(6, "Softdrinks");
        databaseHelper.addFoodGroup(6, "Alkoholisches");
        databaseHelper.addFoodGroup(6, "Säfte");

        databaseHelper.addFoodGroup(7, "Süßigkeiten");                                      // 22
        databaseHelper.addFoodGroup(7, "Fast Food");

        // 3 Milchprodukte, -alternativen und Ei
        databaseHelper.addFood(3,"Ayran", 2, 35, 100);
        databaseHelper.addFood(3,"Crème fraîche", 1, 295, 100);
        databaseHelper.addFood(3,"Cheddar, 48 % Fett", 1, 403, 100);
        databaseHelper.addFood(3,"Emmentaler, 45 % Fett", 1, 396, 100);
        databaseHelper.addFood(3,"Edamer, 45 % Fett", 1, 354, 100);
        databaseHelper.addFood(3,"Ei", 1, 137, 100);
        databaseHelper.addFood(3,"Gouda", 1, 364, 100);
        databaseHelper.addFood(3,"Haferdrink", 2, 104, 100);
        databaseHelper.addFood(3,"Hüttenkäse", 1, 104, 100);
        databaseHelper.addFood(3,"Kokosmilch", 2, 185, 100);
        databaseHelper.addFood(3,"Magerquark", 1, 67, 100);
        databaseHelper.addFood(3,"Mandelmilch", 2, 24, 100);
        databaseHelper.addFood(3,"Milch, 3,8 % Fett", 2, 65, 100);
        databaseHelper.addFood(3,"Milch, fettarm", 2, 47, 100);
        databaseHelper.addFood(3,"Naturjoghurt", 1, 62, 100);
        databaseHelper.addFood(3,"Sahne", 1, 204, 100);
        databaseHelper.addFood(3,"Schmand", 1, 240, 100);
        databaseHelper.addFood(3,"Sojadrink", 2, 36, 100);
        databaseHelper.addFood(3,"Sojajoghurt", 1, 50, 100);
        databaseHelper.addFood(3,"Sojaquark", 1, 71, 100);

        // 5 Hülsenfrüchte

        // 9 Obst
        databaseHelper.addFood(9,"Ananas", 1, 59, 100);
        databaseHelper.addFood(9,"Apfel",1, 54, 100);
        databaseHelper.addFood(9,"Aprikose", 1, 48, 100);
        databaseHelper.addFood(9,"Avocado", 1, 160, 100);
        databaseHelper.addFood(9,"Birne", 1, 52, 100);
        databaseHelper.addFood(9,"Banane", 1, 93, 100);
        databaseHelper.addFood(9,"Blaubeeren", 1, 42, 100);
        databaseHelper.addFood(9,"Brombeeren", 1, 43, 100);
        databaseHelper.addFood(9,"Cranberry", 1, 35, 100);
        databaseHelper.addFood(9,"Dattel", 1, 297, 100);
        databaseHelper.addFood(9,"Erdbeeren", 1, 32, 100);
        databaseHelper.addFood(9,"Feige", 1, 107, 100);
        databaseHelper.addFood(9,"Grapefruit", 1, 50, 100);
        databaseHelper.addFood(9,"Granatapfel", 1, 74, 100);
        databaseHelper.addFood(9,"Hagebutte", 1, 160, 100);
        databaseHelper.addFood(9,"Honigmelone", 1, 54, 100);
        databaseHelper.addFood(9,"Himbeeren", 1, 36, 100);
        databaseHelper.addFood(9,"Johannisbeere", 1, 33,100);
        databaseHelper.addFood(9,"Kiwi", 1, 50, 100);
        databaseHelper.addFood(9,"Kirschen", 1, 50, 100);
        databaseHelper.addFood(9,"Mandarine", 1, 50, 100);
        databaseHelper.addFood(9,"Mango", 1, 62, 100);
        databaseHelper.addFood(9,"Maracuja", 1, 96, 100);
        databaseHelper.addFood(9,"Papaya", 1, 32, 100);
        databaseHelper.addFood(9,"Pflaume", 1, 47, 100);
        databaseHelper.addFood(9,"Pfirsich", 1, 41, 100);
        databaseHelper.addFood(9,"Quitte", 1, 38, 100);
        databaseHelper.addFood(9,"Rhabarber", 1, 21, 100);
        databaseHelper.addFood(9,"Wassermelone", 1, 30, 100);
        databaseHelper.addFood(9,"Weintraube", 1, 70, 100);
        databaseHelper.addFood(9,"Zitrone", 1, 35, 100);

        // 10 Gemüse
        databaseHelper.addFood(8,"Aubergine", 1, 24, 100);
        databaseHelper.addFood(8,"Artischocke", 1, 47, 100);
        databaseHelper.addFood(8,"Blumenkohl", 1, 25, 100);
        databaseHelper.addFood(8,"Brokkoli", 1, 35, 100);
        databaseHelper.addFood(8,"Bohnen, grün", 1, 29, 100);
        databaseHelper.addFood(8,"Champignons", 1, 22, 100);
        databaseHelper.addFood(8,"Chinakohl", 1, 13, 100);
        databaseHelper.addFood(8,"Erbsen", 1, 82, 100);
        databaseHelper.addFood(8,"Eisbergsalat", 1, 14, 100);
        databaseHelper.addFood(8,"Fenchel", 1, 31, 100);
        databaseHelper.addFood(8,"Gurke", 1, 15, 100);
        databaseHelper.addFood(8,"Grünkohl", 1, 49, 100);
        databaseHelper.addFood(8,"Karotte", 1, 36, 100);
        databaseHelper.addFood(8,"Kartoffel", 1, 86, 100);
        databaseHelper.addFood(8,"Kohlrabi", 1, 27, 100);
        databaseHelper.addFood(8,"Kürbis, Hokkaido", 1, 63, 100);
        databaseHelper.addFood(8,"Lauch", 1, 31, 100);
        databaseHelper.addFood(8,"Mais", 1, 71, 100);
        databaseHelper.addFood(8,"Mangold", 1, 19, 100);
        databaseHelper.addFood(8,"Paprika, gelb", 1, 37, 100);
        databaseHelper.addFood(8,"Paprika, rot", 1, 43, 100);
        databaseHelper.addFood(8,"Radieschen", 1, 16, 100);
        databaseHelper.addFood(8,"Rote Bete", 1, 43, 100);
        databaseHelper.addFood(8,"Rosenkohl", 1, 43, 100);
        databaseHelper.addFood(8,"Rucola", 1, 25, 100);
        databaseHelper.addFood(8,"Spargel, grün", 1, 18, 100);
        databaseHelper.addFood(8,"Spargel, weiß", 1, 17, 100);
        databaseHelper.addFood(8,"Spinat", 1, 23, 100);
        databaseHelper.addFood(8,"Süßkartoffel", 1, 76, 100);
        databaseHelper.addFood(8,"Zucchini", 1, 20, 100);
        databaseHelper.addFood(8,"Zwiebel", 1, 28, 100);

        // 11 Fleisch
        databaseHelper.addFood(9,"Bratwurst", 1, 240, 100);
        databaseHelper.addFood(9,"Ente", 1, 310, 100);
        databaseHelper.addFood(9, "Hähnchenbrust", 1, 90, 100);
        databaseHelper.addFood(9,"Kalbfleisch", 1, 95, 100);
        databaseHelper.addFood(9,"Lamm", 1, 150, 100);
        databaseHelper.addFood(9,"Putenbrust", 1, 112, 100);
        databaseHelper.addFood(9,"Salami", 1, 361, 100);
        databaseHelper.addFood(9,"Schinken", 1, 190, 100);
        databaseHelper.addFood(9,"Speck", 1, 645, 100);
        databaseHelper.addFood(9,"Rinderfilet", 1, 120, 100);
        databaseHelper.addFood(9,"Rinderhack", 1, 200, 100);
        databaseHelper.addFood(9,"Schweineschnitzel", 1, 110, 100);
        databaseHelper.addFood(9,"Wiener Würstchen", 1, 260, 100);

        // 12 Fisch

        // 13 Fleischersatz

        // 14 Backwaren

        // 15 Getreideprodukte

        // 16 Kuchen

        // 17 Kaffee & Tee

        // 18 Softdrinks

        // 19 Softdrinks

        // 20 Alkoholisches

        // 21 Säfte

        // 22 Süßigkeiten

        // 23 Fast Food

//        databaseHelper.addRecipe()
    }
}
