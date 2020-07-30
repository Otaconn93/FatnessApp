package com.mobilesysteme.fatnessapp;

import android.util.Log;

/**
 * A class used to fill the Database with hardcoded values
 * @author Hoffmann
 */
public abstract class DatabaseContentHelperUtils {

    /**
     * Fills the data base tables with the following hardcoded entries
     * @param databaseHelper the helper class used to get access to the database
     */
    public static void fillDatabase(DatabaseHelper databaseHelper) {

        try {
            addUnits(databaseHelper);

            addFoodGroups(databaseHelper);

            addFood(databaseHelper);

            addRecipesWithIngredients(databaseHelper);
        } catch (Exception ex) {
            Log.e("DatabaseError", "Failed to fill the DB", ex);
        }
    }

    private static void addRecipesWithIngredients(DatabaseHelper databaseHelper) {

        databaseHelper.addRecipe("Reis mit Geschnetzeltem", "Reis nach Packungsanleitung aufsetzen\n" +
                "Zwiebel und Knoblauch-Zehe schneiden\n" +
                "Öl in Pfanne erhitzen\n" +
                "Zwiebel und Knoblauch in Pfanne andünsten\n" +
                "Hähnchengeschnetzeltes hinzugeben und anbraten\n" +
                "Tomatenstücke hinzugeben\n" +
                "Etwas Wasser hinzufügen\n" +
                "Tomatenmark und Gewürze hinzufügen\n" +
                "15min köcheln lassen\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(1, 136, 200); // reis
        databaseHelper.addRecipeIngredient(1, 100, 200); // hähnchen
        databaseHelper.addRecipeIngredient(1, 95, 50); // tomate
        databaseHelper.addRecipeIngredient(1, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(1, 33, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(1, 34, 5); // Tomatenmark
        databaseHelper.addRecipeIngredient(1, 32, 20); // Olivenöl

        databaseHelper.addRecipe("Reis mit Bohnen", "Reis nach Packungsanleitung aufsetzen\n" +
                "Zwiebel und Knoblauch schneiden\n" +
                "Etwas Olivenöl erhitzen\n" +
                "Die geschnittene Zwiebel und den Knoblauch hinzugeben, bis sie glasig sind\n" +
                "Bohnen hinzugeben, kurz andünsten\n" +
                "Tomatenstücke und ein wenig Wasser hinzufügen\n" +
                "Die oben genannten Gewürze hinzugeben\n" +
                "Den Bohnengulasch kochen, bis die Bohnen weich sind\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(2, 136, 200); // reis
        databaseHelper.addRecipeIngredient(2, 95, 100); // tomate
        databaseHelper.addRecipeIngredient(2, 23, 100); // Brechbohnen
        databaseHelper.addRecipeIngredient(2, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(2, 33, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(2, 32, 20); // Olivenöl

        databaseHelper.addRecipe("Nudeln mit Arrabiata", "Nudeln nach Packungsanleitung aufsetzen\n" +
                "Zwiebeln und Knoblauchzehe schneiden\n" +
                "Olivenöl in Pfanne erhitzen. Zwiebel und Knoblauch andünsten\n" +
                "Tomaten hinzugeben\n" +
                "Ein wenig Wasser hinzufügen\n" +
                "Oben genannte Gewürze hinzufügen\n" +
                "ca.10 min köcheln lassen\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(3, 134, 200); // Nudeln
        databaseHelper.addRecipeIngredient(3, 95, 100); // tomate
        databaseHelper.addRecipeIngredient(3, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(3, 33, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(3, 32, 20); // Olivenöl
        databaseHelper.addRecipeIngredient(3, 3, 50); // Pasta-Käse

        databaseHelper.addRecipe("Bolognese", "Reis oder Nudeln nach Packungsanleitung aufsetzen\n" +
                "Olivenöl in Topf erhitzen\n" +
                "Zwiebeln und Knoblauch-Zehe schneiden und im Topf andünsten\n" +
                "Hackfleisch hinzugeben und kurz anbraten\n" +
                "Tomatenstücke hinzugeben\n" +
                "Oben genannte Gewürze und etwas Tomatenmark hinzugeben\n" +
                "Etwas Wasser hinzugeben\n" +
                "ca. 10 min köcheln lassen\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(4, 136, 200); // Reis oder Nudeln
        databaseHelper.addRecipeIngredient(4, 108, 30); // Hackfleisch (ca. 30 Gramm pro Person)
        databaseHelper.addRecipeIngredient(4, 95, 50); // tomate
        databaseHelper.addRecipeIngredient(4, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(4, 33, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(4, 34, 5); // Tomatenmark

        databaseHelper.addRecipe("Nudeln mit Linsen", "Linsen nach Packungsanleitung aufsetzen (dauert meisten 45-60 min)\n" +
                "Zwiebeln und Knoblauch-Zehe schneiden\n" +
                "Kartoffeln, Karotten und Lauch waschen und schneiden\n" +
                "Olivenöl in Topf erhitzen\n" +
                "Zwiebeln und Knoblauch-Zehe in Topf andünsten\n" +
                "Vorgekochte Linsen hinzugeben\n" +
                "Gemüse hinzugeben\n" +
                "Nudeln nach Packungsanleitung aufsetzen\n" +
                "Gemüse und Linsen köcheln lassen, bis alles gut durch ist\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(5, 134, 200); // Nudeln
        databaseHelper.addRecipeIngredient(5, 26, 100); // Linsen
        databaseHelper.addRecipeIngredient(5, 79, 180); // 2 mittelgroße Kartoffeln
        databaseHelper.addRecipeIngredient(5, 82, 140); // 1 Lauch
        databaseHelper.addRecipeIngredient(5, 78, 124); // 2 Karotten
        databaseHelper.addRecipeIngredient(5, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(5, 33, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(5, 32, 20); // Olivenöl

        databaseHelper.addRecipe("Reis mit Erbsen-Curry", "Reis nach Packungsanleitung aufsetzen\n" +
                "Zwiebeln und Knoblauch-Zehe schneiden\n" +
                "Butter in Topf erhitzen\n" +
                "Zwiebel und Knoblauch-Zehe im Topf andünsten\n" +
                "Erbsen hinzugeben\n" +
                "Gewürze hinzugeben (Currymenge je nach Geschmack anpassen)\n" +
                "Kurz köcheln lassen\n" +
                "Fertig");
        databaseHelper.addRecipeIngredient(6, 136, 200); // Reis
        databaseHelper.addRecipeIngredient(6, 22, 200); // Grüne Erbsen
        databaseHelper.addRecipeIngredient(6, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(6, 33, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(6, 31, 3); // 1 Stück Butter aka rapsöl ;p

        databaseHelper.addRecipe("Räubertopf", "\n" +
                "Reis nach Packungsanleitung aufsetzen\n" +
                "Zwiebeln und Knoblauch schneiden\n" +
                "Kartoffeln und Karotten waschen\n" +
                "Olivenöl in Topf erhitzen\n" +
                "Zwiebeln und Knoblauch in Topf andünsten\n" +
                "Kartoffeln, Karotten und Champignons\n" +
                "Gewürze sowie Tomatenmark und ein wenig Wasser hinzugeben\n" +
                "Köcheln lassen, bis alles „durch“ ist.\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(7, 136, 200); // Reis
        databaseHelper.addRecipeIngredient(7, 79, 270); // 3 mittelgroße Kartoffeln
        databaseHelper.addRecipeIngredient(7, 78, 124); // 2 Karotten
        databaseHelper.addRecipeIngredient(7, 71, 200); // 1 kleiner Teller tiefgefrorener Champignons
        databaseHelper.addRecipeIngredient(7, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(7, 33, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(7, 34, 5); // Tomatenmark
        databaseHelper.addRecipeIngredient(7, 32, 20); // Olivenöl

        databaseHelper.addRecipe("Selbstgemachte Pizza", "Ofen vorheizen\n" +
                "Pizzateig nach Anleitung zubereiten\n" +
                "Tomaten-Passata auf dem Teig verteilen4. Käse auf Pizza verteilen\n" +
                "Puten-Schinken darauf verteilen\n" +
                "Pizza mit Tomaten und Oliven belegen\n" +
                "Pepperoni o. Champignons oben drauf geben\n" +
                "Pizza mit den oben genannten Gewürzen würzen\n" +
                "Pizza in den Ofen geben und bis zur gewünschten Bräune backen\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(8, 138, 200); // Dinkelpizzateig (entweder Fertigmischung oder selbst gemacht)
        databaseHelper.addRecipeIngredient(8, 3, 50); // Pizza-käse
        databaseHelper.addRecipeIngredient(8, 103, 50); // mehrere Scheiben Puten-Schinken
        databaseHelper.addRecipeIngredient(8, 95, 300); // tomate
        // Oliven
        databaseHelper.addRecipeIngredient(8, 71, 200); // 1 kleiner Teller Champignons

        databaseHelper.addRecipe("Selbstgemachter Burger", "Ofen auf ca. 200 Grad vorheizen\n" +
                "Burgerbrötchen aufschneiden\n" +
                "Burgerpatties mit Salz und Pfeffer würzen und in einer Pfanne mit Olivenöl\n" +
                "anbraten\n" +
                "Tomate und Zwiebel schneiden\n" +
                "Burgerbrötchen mit den Zutaten belegen\n" +
                "Burger für 1-2 min in den Ofen geben (nicht zu lange)\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(9, 128, 200); // 2 Burger Brötchen
        databaseHelper.addRecipeIngredient(9, 108, 300);// Burger Patties (aus Hackfleisch)
        databaseHelper.addRecipeIngredient(9, 95, 50); // tomate
        databaseHelper.addRecipeIngredient(9, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(9, 74, 40); // Einige frische Salatblätter
        databaseHelper.addRecipeIngredient(9, 3, 50);// Käse(am besten ist hier Cheddar-Käse)
        databaseHelper.addRecipeIngredient(9, 32, 20); // Olivenöl

        databaseHelper.addRecipe("Selbstgemachte Pommes", "Kartoffeln schälen und in feine Streifen schneiden ( nicht zu dünn schneiden,\n" +
                "sonst werden sie schnell schwarz)\n" +
                "In eine Schüssel geben\n" +
                "Mit 1-2 Esslöffel Olivenöl begießen, umrühren\n" +
                "Pommes etwas salzen\n" +
                "Bei ca. 200 Grad im Backofen backen, bis sie eine goldbraune Farbe haben\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(10, 79, 270); // 2 mittelgroße Kartoffeln
        databaseHelper.addRecipeIngredient(10, 32, 20); // Olivenöl

        databaseHelper.addRecipe("Nudeln mit Gemüse & Feta-Käse", "Nudeln nach Packungsanleitung aufsetzen\n" +
                "Zwiebeln und Knoblauch-Zehe schneiden\n" +
                "Paprika-Schoten, Zucchini und Tomaten waschen und in feine Scheiben\n" +
                "schneiden\n" +
                "Öl in Pfanne erhitzen\n" +
                "Zwiebel und Knoblauch in Pfanne andünsten\n" +
                "Paprika-Streifen, Zucchini und Tomatenscheiben hinzugeben, leicht andünsten\n" +
                "Gewürze hinzugeben\n" +
                "Feta-Käse zerkleinern und auf dem Gemüse zerstreuen\n" +
                "Gemüse zusammen mit den Nudeln anrichten\n" +
                "Fertig!");
        databaseHelper.addRecipeIngredient(11, 134, 200); // Nudeln
        databaseHelper.addRecipeIngredient(11, 85, 310); // 2 Paprika-Schoten
        databaseHelper.addRecipeIngredient(11, 96, 420); // 3 kleine Zucchini
        databaseHelper.addRecipeIngredient(11, 95, 100); // tomate
        databaseHelper.addRecipeIngredient(11, 97, 83); // Zwiebel
        databaseHelper.addRecipeIngredient(11, 32, 3); // Knoblauch-Zehe
        databaseHelper.addRecipeIngredient(11, 9, 150); // ca. 150 Gramm Feta-Käse
        databaseHelper.addRecipeIngredient(11, 32, 20); // Olivenöl
    }

    private static void addFood(DatabaseHelper databaseHelper) {

        // 3 Milchprodukte, -alternativen und Ei
        databaseHelper.addFood(3,"Ayran", 2, 35, 100);                          // 1
        databaseHelper.addFood(3,"Crème fraîche", 1, 295, 100);
        databaseHelper.addFood(3,"Cheddar, 48 % Fett", 1, 403, 100);
        databaseHelper.addFood(3,"Emmentaler, 45 % Fett", 1, 396, 100);
        databaseHelper.addFood(3,"Edamer, 45 % Fett", 1, 354, 100);
        databaseHelper.addFood(3,"Ei", 1, 137, 100);
        databaseHelper.addFood(3,"Gouda", 1, 364, 100);
        databaseHelper.addFood(3,"Haferdrink", 2, 104, 100);
        databaseHelper.addFood(3,"Hüttenkäse", 1, 104, 100);
        databaseHelper.addFood(3,"Kokosmilch", 2, 185, 100);
        databaseHelper.addFood(3,"Magerquark", 1, 67, 100);                     // 11
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
        databaseHelper.addFood(5,"Belugalinsen", 1, 321, 100);                  // 21
        databaseHelper.addFood(5,"Erbsen, gekocht", 1, 85, 100);
        databaseHelper.addFood(5,"Gartenbohne", 1, 31, 100);
        databaseHelper.addFood(5,"Kichererbsen, gekocht", 1, 88, 100);
        databaseHelper.addFood(5,"Kidneybohnen, gekocht", 1, 74, 100);
        databaseHelper.addFood(5,"Linsen, gekocht", 1, 130, 100);
        databaseHelper.addFood(5,"Okraschoten", 1, 29, 100);
        databaseHelper.addFood(5,"Rote Linsen", 1, 316, 100);
        databaseHelper.addFood(5,"Sojabohnen", 1, 340, 100);

        // 8 Sonstiges
        databaseHelper.addFood(8,"Sonnenblumenöl", 2, 884, 100);
        databaseHelper.addFood(8,"Rapsöl", 2, 884, 100);                        // 31
        databaseHelper.addFood(8,"Olivenöl", 2, 884, 100);
        databaseHelper.addFood(8,"Knoblauch", 1, 141, 3);
        databaseHelper.addFood(8,"Tomatenmark", 1, 105 , 5);

        // 9 Obst
        databaseHelper.addFood(9,"Ananas", 1, 59, 100);
        databaseHelper.addFood(9,"Apfel",1, 54, 135);
        databaseHelper.addFood(9,"Aprikose", 1, 48, 100);
        databaseHelper.addFood(9,"Avocado", 1, 160, 160);
        databaseHelper.addFood(9,"Birne", 1, 52, 206);
        databaseHelper.addFood(9,"Banane", 1, 93, 114);
        databaseHelper.addFood(9,"Blaubeeren", 1, 42, 100);                     // 41
        databaseHelper.addFood(9,"Brombeeren", 1, 43, 100);
        databaseHelper.addFood(9,"Cranberry", 1, 35, 100);
        databaseHelper.addFood(9,"Dattel", 1, 297, 5);
        databaseHelper.addFood(9,"Erdbeeren", 1, 32, 100);
        databaseHelper.addFood(9,"Feige", 1, 107, 100);
        databaseHelper.addFood(9,"Grapefruit", 1, 50, 350);
        databaseHelper.addFood(9,"Granatapfel", 1, 74, 100);
        databaseHelper.addFood(9,"Hagebutte", 1, 160, 100);
        databaseHelper.addFood(9,"Honigmelone", 1, 54, 869);
        databaseHelper.addFood(9,"Himbeeren", 1, 36, 100);                      // 51
        databaseHelper.addFood(9,"Johannisbeere", 1, 33,100);
        databaseHelper.addFood(9,"Kiwi", 1, 50, 100);
        databaseHelper.addFood(9,"Kirschen", 1, 50, 100);
        databaseHelper.addFood(9,"Mandarine", 1, 50, 125);
        databaseHelper.addFood(9,"Mango", 1, 62, 250);
        databaseHelper.addFood(9,"Maracuja", 1, 96, 100);
        databaseHelper.addFood(9,"Papaya", 1, 32, 100);
        databaseHelper.addFood(9,"Pflaume", 1, 47, 100);
        databaseHelper.addFood(9,"Pfirsich", 1, 41, 100);
        databaseHelper.addFood(9,"Quitte", 1, 38, 100);                         // 61
        databaseHelper.addFood(9,"Rhabarber", 1, 21, 100);
        databaseHelper.addFood(9,"Wassermelone", 1, 30, 100);
        databaseHelper.addFood(9,"Weintraube", 1, 70, 100);
        databaseHelper.addFood(9,"Zitrone", 1, 35, 80);

        // 10 Gemüse und Nachtschattengewächse
        databaseHelper.addFood(10,"Aubergine", 1, 24, 100);
        databaseHelper.addFood(10,"Artischocke", 1, 47, 100);
        databaseHelper.addFood(10,"Blumenkohl", 1, 25, 879);
        databaseHelper.addFood(10,"Brokkoli", 1, 35, 100);
        databaseHelper.addFood(10,"Bohnen, grün", 1, 29, 100);
        databaseHelper.addFood(10,"Champignons", 1, 22, 100);                   // 71
        databaseHelper.addFood(10,"Chinakohl", 1, 13, 100);
        databaseHelper.addFood(10,"Erbsen", 1, 82, 100);
        databaseHelper.addFood(10,"Eisbergsalat", 1, 14, 100);
        databaseHelper.addFood(10,"Fenchel", 1, 31, 286);
        databaseHelper.addFood(10,"Gurke", 1, 15, 550);
        databaseHelper.addFood(10,"Grünkohl", 1, 49, 100);
        databaseHelper.addFood(10,"Karotte", 1, 36, 62);
        databaseHelper.addFood(10,"Kartoffel", 1, 86, 90);
        databaseHelper.addFood(10,"Kohlrabi", 1, 27, 265);
        databaseHelper.addFood(10,"Kürbis, Hokkaido", 1, 63, 100);              // 81
        databaseHelper.addFood(10,"Lauch", 1, 31, 140);
        databaseHelper.addFood(10,"Mais", 1, 71, 100);
        databaseHelper.addFood(10, "Mangold", 1, 19, 100);
        databaseHelper.addFood(10,"Paprika, gelb", 1, 37, 155);
        databaseHelper.addFood(10,"Paprika, rot", 1, 43, 155);
        databaseHelper.addFood(10,"Radieschen", 1, 16, 100);
        databaseHelper.addFood(10,"Rote Bete", 1, 43, 100);
        databaseHelper.addFood(10,"Rosenkohl", 1, 43, 100);
        databaseHelper.addFood(10,"Rucola", 1, 25, 100);
        databaseHelper.addFood(10,"Spargel, grün", 1, 18, 100);                 // 91
        databaseHelper.addFood(10,"Spargel, weiß", 1, 17, 100);
        databaseHelper.addFood(10,"Spinat", 1, 23, 100);
        databaseHelper.addFood(10,"Süßkartoffel", 1, 76, 310 );
        databaseHelper.addFood(10,"Tomate", 1, 18, 110);
        databaseHelper.addFood(10,"Zucchini", 1, 20, 210);
        databaseHelper.addFood(10,"Zwiebel", 1, 28, 83);

        // 11 Fleisch
        databaseHelper.addFood(11,"Bratwurst", 1, 240, 100);
        databaseHelper.addFood(11,"Ente", 1, 310, 100);
        databaseHelper.addFood(11, "Hähnchenbrust", 1, 90, 100);
        databaseHelper.addFood(11,"Kalbfleisch", 1, 95, 100);                   // 101
        databaseHelper.addFood(11,"Lamm", 1, 150, 100);
        databaseHelper.addFood(11,"Putenbrust", 1, 112, 100);
        databaseHelper.addFood(11,"Salami", 1, 361, 100);
        databaseHelper.addFood(11,"Schinken", 1, 190, 100);
        databaseHelper.addFood(11,"Speck", 1, 645, 100);
        databaseHelper.addFood(11,"Rinderfilet", 1, 120, 100);
        databaseHelper.addFood(11,"Rinderhack", 1, 200, 100);
        databaseHelper.addFood(11,"Schweineschnitzel", 1, 110, 100);
        databaseHelper.addFood(11,"Wiener Würstchen", 1, 260, 100);

        // 12 Fisch
        databaseHelper.addFood(12,"Forelle", 1, 107, 100);                      // 111
        databaseHelper.addFood(12,"Hecht", 1, 113, 100);
        databaseHelper.addFood(12,"Hering", 1, 158, 100);
        databaseHelper.addFood(12,"Lachs", 1, 142, 100);
        databaseHelper.addFood(12,"Rotbarschfilet", 1, 121, 100);
        databaseHelper.addFood(12,"Thunfisch", 1, 103, 100);
        databaseHelper.addFood(12,"Zander", 1, 83, 100);

        // 13 Fleischersatz
        databaseHelper.addFood(13,"Seitan", 1, 139, 100);
        databaseHelper.addFood(13,"Tempeh", 1, 165, 100);
        databaseHelper.addFood(13,"Tofu, natur", 1, 124, 100);
        databaseHelper.addFood(13,"Tofu, geräuchert", 1, 165, 100);             // 121

        // 14 Backwaren
        databaseHelper.addFood(14,"Blätterteig", 1, 372, 100);
        databaseHelper.addFood(14,"Knäckebrot", 1, 334, 100);
        databaseHelper.addFood(14,"Pumpernickel", 1, 199, 100);
        databaseHelper.addFood(14,"Roggenvollkornbrot", 1, 210, 100);
        databaseHelper.addFood(14,"Salzstangen", 1, 393, 100);
        databaseHelper.addFood(14,"Toastbrot", 1, 258, 100);
        databaseHelper.addFood(14,"Weizenbrötchen", 1, 262, 100);
        databaseHelper.addFood(14,"Zwieback", 1, 401, 100);

        // 15 Getreideprodukte
        databaseHelper.addFood(15,"Bulgur", 1, 342, 100);
        databaseHelper.addFood(15,"Couscous", 1, 376, 100);                     // 131
        databaseHelper.addFood(15,"Haferflocken", 1, 372, 100);
        databaseHelper.addFood(15,"Hirse", 1, 362, 100);
        databaseHelper.addFood(15,"Nudeln", 1, 144, 100);
        databaseHelper.addFood(15,"Quinoa (Pseudogetreide)", 1, 354, 100);
        databaseHelper.addFood(15,"Reis", 1, 352, 100);
        databaseHelper.addFood(15,"Weizenmehl", 1, 348, 100);
        databaseHelper.addFood(15,"Vollkornweizenmehl", 1, 326, 100);

        // 16 Kuchen
        databaseHelper.addFood(16,"Bananenbrot", 1, 311, 100);
        databaseHelper.addFood(16,"Brownie", 1, 430, 100);
        databaseHelper.addFood(16,"Butterkuchen", 1, 330, 100);                 // 141
        databaseHelper.addFood(16,"Käsekuchen", 1, 293, 100);
        databaseHelper.addFood(16,"Marmorkuchen", 1, 380, 100);
        databaseHelper.addFood(16,"Nussecke", 1, 540, 100);
        databaseHelper.addFood(16,"Schwarzwälder Kirschtorte", 1, 334, 100);
        databaseHelper.addFood(16,"Zwetschgenkuchen", 1, 170, 100);

        // 17 Kaffee & Tee
        databaseHelper.addFood(17,"Capuccino", 2, 47, 200);
        databaseHelper.addFood(17,"Chai Tee Latte", 2, 51, 200);
        databaseHelper.addFood(17,"Eiskaffee", 2, 70, 200);
        databaseHelper.addFood(17,"Filterkaffee", 2, 2, 200);
        databaseHelper.addFood(17,"Grünter Tee", 2, 1, 200);                    // 151
        databaseHelper.addFood(17,"Latte Macchiato", 2, 47, 200);
        databaseHelper.addFood(17,"Milchkaffee", 2, 33, 200);
        databaseHelper.addFood(17,"Schwarzer Tee", 2, 1, 200);

        // 18 Softdrinks
        databaseHelper.addFood(18,"Apfelschorle", 2, 22, 200);
        databaseHelper.addFood(18,"Club Mate", 2, 20, 200);
        databaseHelper.addFood(18,"Coca Cola", 2, 42, 200);
        databaseHelper.addFood(18,"Coca Cola Zero", 2, 0, 200);
        databaseHelper.addFood(18,"Fanta", 2, 38, 200);
        databaseHelper.addFood(18,"Red Bull", 2, 46, 200);
        databaseHelper.addFood(18,"Rhabarberschorle", 2, 28, 200);              // 161
        databaseHelper.addFood(18,"Sprite", 2, 39, 200);
        databaseHelper.addFood(18,"Vitamalz", 2, 42, 200);

        // 19 Alkoholisches
        databaseHelper.addFood(19,"Alster / Radler", 2, 39, 300);
        databaseHelper.addFood(19,"Aperol Spritz", 2, 140, 200);
        databaseHelper.addFood(19,"Cider", 2, 65, 300);
        databaseHelper.addFood(19,"Baileys", 2, 334, 20);
        databaseHelper.addFood(19,"Kölsch", 2, 46, 500);
        databaseHelper.addFood(19,"Pils", 2, 39, 500);
        databaseHelper.addFood(19,"Rotwein", 2, 84, 200);
        databaseHelper.addFood(19,"Sekt", 2, 83, 100);                          // 171
        databaseHelper.addFood(19,"Vodka", 2, 215, 20);
        databaseHelper.addFood(19,"Rum", 2, 260, 20);
        databaseHelper.addFood(19,"Weißwein", 2, 71, 200);

        // 20 Säfte
        databaseHelper.addFood(20,"Ananassaft", 2, 50, 200);
        databaseHelper.addFood(20,"Apfelsaft", 2, 48, 200);
        databaseHelper.addFood(20,"Grapefruitsaft", 2, 39, 200);
        databaseHelper.addFood(20,"Kirschsaft", 2, 55, 200);
        databaseHelper.addFood(20,"Orangensaft", 2, 42, 200);
        databaseHelper.addFood(20,"Orangensaft frisch", 2, 45, 200);
        databaseHelper.addFood(20,"Tomatensaft", 2, 18, 200);                   // 181

        // 21 Süßigkeiten
        databaseHelper.addFood(21,"Balisto", 1, 499, 100);
        databaseHelper.addFood(21,"Eierwaffel", 1, 341, 100);
        databaseHelper.addFood(21,"Gebrannte Mandeln", 1, 503, 100);
        databaseHelper.addFood(21,"Haferkeks", 1, 415, 100);
        databaseHelper.addFood(21,"Löffelbiskuit", 1, 407, 100);
        databaseHelper.addFood(21,"M&M's", 1, 484, 100);
        databaseHelper.addFood(21,"Nutella", 1, 539, 100);
        databaseHelper.addFood(21,"Popcorn", 1, 364, 100);
        databaseHelper.addFood(21,"Russisches Brot", 1, 385, 100);
        databaseHelper.addFood(21,"Spekulatius", 1, 484, 100);                  // 191
        databaseHelper.addFood(21,"Studentenfutter", 1, 575, 100);
        databaseHelper.addFood(21,"Vollkornkekse", 1, 411, 100);
        databaseHelper.addFood(21,"Zimtsterne", 1, 395, 100);
        databaseHelper.addFood(21,"Zuckerwatte", 1, 410, 100);

        // 22 Fast Food
        databaseHelper.addFood(22,"Cheeseburger", 1, 254, 100);
        databaseHelper.addFood(22,"Chicken Wings", 1, 191, 100);
        databaseHelper.addFood(22,"Döner Sandwich", 1, 141, 100);
        databaseHelper.addFood(22,"Falafel Dürüm", 1, 132, 100);
        databaseHelper.addFood(22,"Frühlingsrollen", 1, 230, 100);
        databaseHelper.addFood(22,"Gebratene Asia-Nudeln", 1, 342, 100);        // 201
        databaseHelper.addFood(22,"Grillhähnchen", 1, 233, 100);
        databaseHelper.addFood(22,"Hot Dog", 1, 170, 100);
        databaseHelper.addFood(22,"Lahmacun", 1, 259, 100);
        databaseHelper.addFood(22,"Nasi Goreng", 1, 101, 100);
        databaseHelper.addFood(22,"Pizza Salami", 1, 245, 100);
        databaseHelper.addFood(22,"Pizza Tonno", 1, 230, 100);
        databaseHelper.addFood(22,"Pommes Frites", 1, 291, 100);
        databaseHelper.addFood(22,"Veggie Burger", 1, 230, 100);                // 209
    }

    private static void addFoodGroups(DatabaseHelper databaseHelper) {

        databaseHelper.addFoodGroup(-1, "Obst und Gemüse");                                 // 1
        databaseHelper.addFoodGroup(-1, "Fleisch, Fisch und Fleischersatz");
        databaseHelper.addFoodGroup(-1, "Milchprodukte, -alternativen und Ei");
        databaseHelper.addFoodGroup(-1, "Backwaren und Getreideprodukte");
        databaseHelper.addFoodGroup(-1, "Hülsenfrüchte");
        databaseHelper.addFoodGroup(-1, "Getränke");
        databaseHelper.addFoodGroup(-1, "Süßigkeiten und Fast Food");
        databaseHelper.addFoodGroup(-1, "Sonstiges");

        databaseHelper.addFoodGroup(1, "Obst");                                             // 9
        databaseHelper.addFoodGroup(1, "Gemüse und Nachtschattengewächse");

        databaseHelper.addFoodGroup(2, "Fleisch");                                          // 11
        databaseHelper.addFoodGroup(2, "Fisch");
        databaseHelper.addFoodGroup(2, "Fleischersatz");

        databaseHelper.addFoodGroup(4, "Backwaren");                                        // 14
        databaseHelper.addFoodGroup(4, "Getreideprodukte");
        databaseHelper.addFoodGroup(4, "Kuchen");

        databaseHelper.addFoodGroup(6, "Kaffee & Tee");                                     // 17
        databaseHelper.addFoodGroup(6, "Softdrinks");
        databaseHelper.addFoodGroup(6, "Alkoholisches");
        databaseHelper.addFoodGroup(6, "Säfte");

        databaseHelper.addFoodGroup(7, "Süßigkeiten");                                      // 21
        databaseHelper.addFoodGroup(7, "Fast Food");
    }

    private static void addUnits(DatabaseHelper databaseHelper) {

        databaseHelper.addUnit("Milligram", "mg");
        databaseHelper.addUnit("Milliliter", "ml");
    }
}
