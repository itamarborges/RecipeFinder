package com.example.itamarborges.recipefinder.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import com.example.itamarborges.recipefinder.BuildConfig;
import com.example.itamarborges.recipefinder.pojo.Ingredient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

/**
 * Created by itamarborges on 29/12/17.
 */

public class NetworkUtils {

    final static String EDAMAM_API_URL =
            "https://api.edamam.com/";

    public final static String EDAMAM_API_SEARCH = "search";


    public final static String EDAMAM_API_QUERY = "q";

    final static String EDAMAM_API_KEY = "app_key";
    final static String EDAMAM_API_ID = "app_id";

    final static String API_KEY = BuildConfig.API_KEY;
    final static String API_ID = BuildConfig.API_ID;


    public static URL buildUrlSearchEdamam(List<Ingredient> ingredients) {

        StringBuffer sbIngredients = new StringBuffer();
        for (Ingredient ing : ingredients){
            sbIngredients.append(ing.getName()).append(",");
        }
        sbIngredients.deleteCharAt(sbIngredients.lastIndexOf(","));

         Uri builtUri = Uri.parse(EDAMAM_API_URL).buildUpon()
                .appendEncodedPath(EDAMAM_API_SEARCH)
                .appendQueryParameter(EDAMAM_API_QUERY, sbIngredients.toString())
                .appendQueryParameter(EDAMAM_API_ID, API_ID)
                .appendQueryParameter(EDAMAM_API_KEY, API_KEY)
                 .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(String s) {
        return "{\n" +
                "  \"q\" : \"potatoes,garlic\",\n" +
                "  \"from\" : 0,\n" +
                "  \"to\" : 10,\n" +
                "  \"params\" : {\n" +
                "    \"sane\" : [ ],\n" +
                "    \"q\" : [ \"potatoes,garlic\" ],\n" +
                "    \"app_key\" : [ \"08c446c58fad7375484e3a6ad41e202c\" ],\n" +
                "    \"app_id\" : [ \"bdc3c6f9\" ]\n" +
                "  },\n" +
                "  \"more\" : true,\n" +
                "  \"count\" : 39377,\n" +
                "  \"hits\" : [ {\n" +
                "    \"recipe\" : {\n" +
                "      \"uri\" : \"http://www.edamam.com/ontologies/edamam.owl#recipe_4528c0b32dfda3e081f1b63b17704d75\",\n" +
                "      \"label\" : \"Spanish Chicken With Potatoes, Garlic & Sherry\",\n" +
                "      \"image\" : \"https://www.edamam.com/web-img/3fd/3fd6ca42a79f25c2c6757eca655ab2d9.jpg\",\n" +
                "      \"source\" : \"BBC Good Food\",\n" +
                "      \"url\" : \"http://www.bbcgoodfood.com/recipes/5036/\",\n" +
                "      \"shareAs\" : \"http://www.edamam.com/recipe/spanish-chicken-with-potatoes-garlic-sherry-4528c0b32dfda3e081f1b63b17704d75/potatoes%2Cgarlic\",\n" +
                "      \"yield\" : 4.0,\n" +
                "      \"dietLabels\" : [ \"Low-Carb\" ],\n" +
                "      \"healthLabels\" : [ \"Sugar-Conscious\", \"Peanut-Free\", \"Tree-Nut-Free\" ],\n" +
                "      \"cautions\" : [ ],\n" +
                "      \"ingredientLines\" : [ \"olive oil\", \"600.0g small potatoes , sliced\", \"125.0ml Amontillado sherry\", \"8 chicken thighs , skin on, or 1 whole chicken cut into 8 portions\", \"8 sprigs thyme\", \"3 whole heads garlic , cloves separated but not peeled\" ],\n" +
                "      \"ingredients\" : [ {\n" +
                "        \"text\" : \"olive oil\",\n" +
                "        \"weight\" : 112.31817323849744\n" +
                "      }, {\n" +
                "        \"text\" : \"600.0g small potatoes , sliced\",\n" +
                "        \"weight\" : 600.0\n" +
                "      }, {\n" +
                "        \"text\" : \"125.0ml Amontillado sherry\",\n" +
                "        \"weight\" : 124.68920871304604\n" +
                "      }, {\n" +
                "        \"text\" : \"8 chicken thighs , skin on, or 1 whole chicken cut into 8 portions\",\n" +
                "        \"weight\" : 7360.0\n" +
                "      }, {\n" +
                "        \"text\" : \"8 sprigs thyme\",\n" +
                "        \"weight\" : 24.0\n" +
                "      }, {\n" +
                "        \"text\" : \"3 whole heads garlic , cloves separated but not peeled\",\n" +
                "        \"weight\" : 150.0\n" +
                "      } ],\n" +
                "      \"calories\" : 17716.160248672146,\n" +
                "      \"totalWeight\" : 8371.007381951544,\n" +
                "      \"totalTime\" : 0.0,\n" +
                "      \"totalNutrients\" : {\n" +
                "        \"ENERC_KCAL\" : {\n" +
                "          \"label\" : \"Energy\",\n" +
                "          \"quantity\" : 17716.16024867215,\n" +
                "          \"unit\" : \"kcal\"\n" +
                "        },\n" +
                "        \"FAT\" : {\n" +
                "          \"label\" : \"Fat\",\n" +
                "          \"quantity\" : 1222.4273732384975,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"FASAT\" : {\n" +
                "          \"label\" : \"Saturated\",\n" +
                "          \"quantity\" : 333.12647336077174,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"FATRN\" : {\n" +
                "          \"label\" : \"Trans\",\n" +
                "          \"quantity\" : 7.139200000000001,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"FAMS\" : {\n" +
                "          \"label\" : \"Monounsaturated\",\n" +
                "          \"quantity\" : 541.2604023765401,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"FAPU\" : {\n" +
                "          \"label\" : \"Polyunsaturated\",\n" +
                "          \"quantity\" : 250.30642136988712,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"CHOCDF\" : {\n" +
                "          \"label\" : \"Carbs\",\n" +
                "          \"quantity\" : 174.82923065681246,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"FIBTG\" : {\n" +
                "          \"label\" : \"Fiber\",\n" +
                "          \"quantity\" : 19.71,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"SUGAR\" : {\n" +
                "          \"label\" : \"Sugars\",\n" +
                "          \"quantity\" : 7.539112374972202,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"PROCNT\" : {\n" +
                "          \"label\" : \"Protein\",\n" +
                "          \"quantity\" : 1392.2037784174263,\n" +
                "          \"unit\" : \"g\"\n" +
                "        },\n" +
                "        \"CHOLE\" : {\n" +
                "          \"label\" : \"Cholesterol\",\n" +
                "          \"quantity\" : 5520.000000000001,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"NA\" : {\n" +
                "          \"label\" : \"Sodium\",\n" +
                "          \"quantity\" : 5229.128392248945,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"CA\" : {\n" +
                "          \"label\" : \"Calcium\",\n" +
                "          \"quantity\" : 1261.3983184294289,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"MG\" : {\n" +
                "          \"label\" : \"Magnesium\",\n" +
                "          \"quantity\" : 1697.1220287841745,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"K\" : {\n" +
                "          \"label\" : \"Potassium\",\n" +
                "          \"quantity\" : 17299.89725374839,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"FE\" : {\n" +
                "          \"label\" : \"Iron\",\n" +
                "          \"quantity\" : 78.58623587104691,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"ZN\" : {\n" +
                "          \"label\" : \"Zinc\",\n" +
                "          \"quantity\" : 100.41768244609914,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"P\" : {\n" +
                "          \"label\" : \"Phosphorus\",\n" +
                "          \"quantity\" : 11427.362028784175,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"VITA_RAE\" : {\n" +
                "          \"label\" : \"Vitamin A\",\n" +
                "          \"quantity\" : 3074.7200000000003,\n" +
                "          \"unit\" : \"µg\"\n" +
                "        },\n" +
                "        \"VITC\" : {\n" +
                "          \"label\" : \"Vitamin C\",\n" +
                "          \"quantity\" : 321.184,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"THIA\" : {\n" +
                "          \"label\" : \"Thiamin (B1)\",\n" +
                "          \"quantity\" : 5.229964057568348,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"RIBF\" : {\n" +
                "          \"label\" : \"Riboflavin (B2)\",\n" +
                "          \"quantity\" : 9.324484057568348,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"NIA\" : {\n" +
                "          \"label\" : \"Niacin (B3)\",\n" +
                "          \"quantity\" : 508.6309480145589,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"VITB6A\" : {\n" +
                "          \"label\" : \"Vitamin B6\",\n" +
                "          \"quantity\" : 29.46602,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"FOLDFE\" : {\n" +
                "          \"label\" : \"Folate equivalent (total)\",\n" +
                "          \"quantity\" : 552.9,\n" +
                "          \"unit\" : \"µg\"\n" +
                "        },\n" +
                "        \"FOLFD\" : {\n" +
                "          \"label\" : \"Folate (food)\",\n" +
                "          \"quantity\" : 552.9,\n" +
                "          \"unit\" : \"µg\"\n" +
                "        },\n" +
                "        \"VITB12\" : {\n" +
                "          \"label\" : \"Vitamin B12\",\n" +
                "          \"quantity\" : 22.816000000000003,\n" +
                "          \"unit\" : \"µg\"\n" +
                "        },\n" +
                "        \"VITD\" : {\n" +
                "          \"label\" : \"Vitamin D\",\n" +
                "          \"quantity\" : 14.720000000000002,\n" +
                "          \"unit\" : \"µg\"\n" +
                "        },\n" +
                "        \"TOCPHA\" : {\n" +
                "          \"label\" : \"Vitamin E\",\n" +
                "          \"quantity\" : 38.37765785972438,\n" +
                "          \"unit\" : \"mg\"\n" +
                "        },\n" +
                "        \"VITK1\" : {\n" +
                "          \"label\" : \"Vitamin K\",\n" +
                "          \"quantity\" : 191.96554028957547,\n" +
                "          \"unit\" : \"µg\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"totalDaily\" : {\n" +
                "        \"ENERC_KCAL\" : {\n" +
                "          \"label\" : \"Energy\",\n" +
                "          \"quantity\" : 885.8080124336075,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"FAT\" : {\n" +
                "          \"label\" : \"Fat\",\n" +
                "          \"quantity\" : 1880.6574972899962,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"FASAT\" : {\n" +
                "          \"label\" : \"Saturated\",\n" +
                "          \"quantity\" : 1665.6323668038585,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"CHOCDF\" : {\n" +
                "          \"label\" : \"Carbs\",\n" +
                "          \"quantity\" : 58.27641021893749,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"FIBTG\" : {\n" +
                "          \"label\" : \"Fiber\",\n" +
                "          \"quantity\" : 78.84,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"PROCNT\" : {\n" +
                "          \"label\" : \"Protein\",\n" +
                "          \"quantity\" : 2784.4075568348526,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"CHOLE\" : {\n" +
                "          \"label\" : \"Cholesterol\",\n" +
                "          \"quantity\" : 1840.0000000000005,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"NA\" : {\n" +
                "          \"label\" : \"Sodium\",\n" +
                "          \"quantity\" : 217.88034967703936,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"CA\" : {\n" +
                "          \"label\" : \"Calcium\",\n" +
                "          \"quantity\" : 126.1398318429429,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"MG\" : {\n" +
                "          \"label\" : \"Magnesium\",\n" +
                "          \"quantity\" : 424.28050719604363,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"K\" : {\n" +
                "          \"label\" : \"Potassium\",\n" +
                "          \"quantity\" : 494.28277867852546,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"FE\" : {\n" +
                "          \"label\" : \"Iron\",\n" +
                "          \"quantity\" : 436.59019928359396,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"ZN\" : {\n" +
                "          \"label\" : \"Zinc\",\n" +
                "          \"quantity\" : 669.4512163073275,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"P\" : {\n" +
                "          \"label\" : \"Phosphorus\",\n" +
                "          \"quantity\" : 1632.4802898263106,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"VITA_RAE\" : {\n" +
                "          \"label\" : \"Vitamin A\",\n" +
                "          \"quantity\" : 341.6355555555556,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"VITC\" : {\n" +
                "          \"label\" : \"Vitamin C\",\n" +
                "          \"quantity\" : 535.3066666666667,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"THIA\" : {\n" +
                "          \"label\" : \"Thiamin (B1)\",\n" +
                "          \"quantity\" : 348.66427050455655,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"RIBF\" : {\n" +
                "          \"label\" : \"Riboflavin (B2)\",\n" +
                "          \"quantity\" : 548.4990622099028,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"NIA\" : {\n" +
                "          \"label\" : \"Niacin (B3)\",\n" +
                "          \"quantity\" : 2543.1547400727945,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"VITB6A\" : {\n" +
                "          \"label\" : \"Vitamin B6\",\n" +
                "          \"quantity\" : 1473.301,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"FOLDFE\" : {\n" +
                "          \"label\" : \"Folate equivalent (total)\",\n" +
                "          \"quantity\" : 138.225,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"VITB12\" : {\n" +
                "          \"label\" : \"Vitamin B12\",\n" +
                "          \"quantity\" : 380.2666666666667,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"VITD\" : {\n" +
                "          \"label\" : \"Vitamin D\",\n" +
                "          \"quantity\" : 3.6800000000000006,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"TOCPHA\" : {\n" +
                "          \"label\" : \"Vitamin E\",\n" +
                "          \"quantity\" : 191.8882892986219,\n" +
                "          \"unit\" : \"%\"\n" +
                "        },\n" +
                "        \"VITK1\" : {\n" +
                "          \"label\" : \"Vitamin K\",\n" +
                "          \"quantity\" : 239.95692536196935,\n" +
                "          \"unit\" : \"%\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"digest\" : [ {\n" +
                "        \"label\" : \"Fat\",\n" +
                "        \"tag\" : \"FAT\",\n" +
                "        \"schemaOrgTag\" : \"fatContent\",\n" +
                "        \"total\" : 1222.4273732384975,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 1880.6574972899962,\n" +
                "        \"unit\" : \"g\",\n" +
                "        \"sub\" : [ {\n" +
                "          \"label\" : \"Saturated\",\n" +
                "          \"tag\" : \"FASAT\",\n" +
                "          \"schemaOrgTag\" : \"saturatedFatContent\",\n" +
                "          \"total\" : 333.12647336077174,\n" +
                "          \"hasRDI\" : true,\n" +
                "          \"daily\" : 1665.6323668038585,\n" +
                "          \"unit\" : \"g\"\n" +
                "        }, {\n" +
                "          \"label\" : \"Trans\",\n" +
                "          \"tag\" : \"FATRN\",\n" +
                "          \"schemaOrgTag\" : \"transFatContent\",\n" +
                "          \"total\" : 7.139200000000001,\n" +
                "          \"hasRDI\" : false,\n" +
                "          \"daily\" : 0.0,\n" +
                "          \"unit\" : \"g\"\n" +
                "        }, {\n" +
                "          \"label\" : \"Monounsaturated\",\n" +
                "          \"tag\" : \"FAMS\",\n" +
                "          \"schemaOrgTag\" : null,\n" +
                "          \"total\" : 541.2604023765401,\n" +
                "          \"hasRDI\" : false,\n" +
                "          \"daily\" : 0.0,\n" +
                "          \"unit\" : \"g\"\n" +
                "        }, {\n" +
                "          \"label\" : \"Polyunsaturated\",\n" +
                "          \"tag\" : \"FAPU\",\n" +
                "          \"schemaOrgTag\" : null,\n" +
                "          \"total\" : 250.30642136988712,\n" +
                "          \"hasRDI\" : false,\n" +
                "          \"daily\" : 0.0,\n" +
                "          \"unit\" : \"g\"\n" +
                "        } ]\n" +
                "      }, {\n" +
                "        \"label\" : \"Carbs\",\n" +
                "        \"tag\" : \"CHOCDF\",\n" +
                "        \"schemaOrgTag\" : \"carbohydrateContent\",\n" +
                "        \"total\" : 174.82923065681246,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 58.27641021893749,\n" +
                "        \"unit\" : \"g\",\n" +
                "        \"sub\" : [ {\n" +
                "          \"label\" : \"Carbs (net)\",\n" +
                "          \"tag\" : \"CHOCDF.net\",\n" +
                "          \"schemaOrgTag\" : null,\n" +
                "          \"total\" : 155.11923065681245,\n" +
                "          \"hasRDI\" : false,\n" +
                "          \"daily\" : 0.0,\n" +
                "          \"unit\" : \"g\"\n" +
                "        }, {\n" +
                "          \"label\" : \"Fiber\",\n" +
                "          \"tag\" : \"FIBTG\",\n" +
                "          \"schemaOrgTag\" : \"fiberContent\",\n" +
                "          \"total\" : 19.71,\n" +
                "          \"hasRDI\" : true,\n" +
                "          \"daily\" : 78.84,\n" +
                "          \"unit\" : \"g\"\n" +
                "        }, {\n" +
                "          \"label\" : \"Sugars\",\n" +
                "          \"tag\" : \"SUGAR\",\n" +
                "          \"schemaOrgTag\" : \"sugarContent\",\n" +
                "          \"total\" : 7.539112374972202,\n" +
                "          \"hasRDI\" : false,\n" +
                "          \"daily\" : 0.0,\n" +
                "          \"unit\" : \"g\"\n" +
                "        }, {\n" +
                "          \"label\" : \"Sugars, added\",\n" +
                "          \"tag\" : \"SUGAR.added\",\n" +
                "          \"schemaOrgTag\" : null,\n" +
                "          \"total\" : 0.0,\n" +
                "          \"hasRDI\" : false,\n" +
                "          \"daily\" : 0.0,\n" +
                "          \"unit\" : \"g\"\n" +
                "        } ]\n" +
                "      }, {\n" +
                "        \"label\" : \"Protein\",\n" +
                "        \"tag\" : \"PROCNT\",\n" +
                "        \"schemaOrgTag\" : \"proteinContent\",\n" +
                "        \"total\" : 1392.2037784174263,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 2784.4075568348526,\n" +
                "        \"unit\" : \"g\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Cholesterol\",\n" +
                "        \"tag\" : \"CHOLE\",\n" +
                "        \"schemaOrgTag\" : \"cholesterolContent\",\n" +
                "        \"total\" : 5520.000000000001,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 1840.0000000000005,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Sodium\",\n" +
                "        \"tag\" : \"NA\",\n" +
                "        \"schemaOrgTag\" : \"sodiumContent\",\n" +
                "        \"total\" : 5229.128392248945,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 217.88034967703936,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Calcium\",\n" +
                "        \"tag\" : \"CA\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 1261.3983184294289,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 126.1398318429429,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Magnesium\",\n" +
                "        \"tag\" : \"MG\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 1697.1220287841745,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 424.28050719604363,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Potassium\",\n" +
                "        \"tag\" : \"K\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 17299.89725374839,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 494.28277867852546,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Iron\",\n" +
                "        \"tag\" : \"FE\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 78.58623587104691,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 436.59019928359396,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Zinc\",\n" +
                "        \"tag\" : \"ZN\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 100.41768244609914,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 669.4512163073275,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Phosphorus\",\n" +
                "        \"tag\" : \"P\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 11427.362028784175,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 1632.4802898263106,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Vitamin A\",\n" +
                "        \"tag\" : \"VITA_RAE\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 3074.7200000000003,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 341.6355555555556,\n" +
                "        \"unit\" : \"µg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Vitamin C\",\n" +
                "        \"tag\" : \"VITC\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 321.184,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 535.3066666666667,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Thiamin (B1)\",\n" +
                "        \"tag\" : \"THIA\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 5.229964057568348,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 348.66427050455655,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Riboflavin (B2)\",\n" +
                "        \"tag\" : \"RIBF\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 9.324484057568348,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 548.4990622099028,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Niacin (B3)\",\n" +
                "        \"tag\" : \"NIA\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 508.6309480145589,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 2543.1547400727945,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Vitamin B6\",\n" +
                "        \"tag\" : \"VITB6A\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 29.46602,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 1473.301,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Folate equivalent (total)\",\n" +
                "        \"tag\" : \"FOLDFE\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 552.9,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 138.225,\n" +
                "        \"unit\" : \"µg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Folate (food)\",\n" +
                "        \"tag\" : \"FOLFD\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 552.9,\n" +
                "        \"hasRDI\" : false,\n" +
                "        \"daily\" : 0.0,\n" +
                "        \"unit\" : \"µg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Folic acid\",\n" +
                "        \"tag\" : \"FOLAC\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 0.0,\n" +
                "        \"hasRDI\" : false,\n" +
                "        \"daily\" : 0.0,\n" +
                "        \"unit\" : \"µg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Vitamin B12\",\n" +
                "        \"tag\" : \"VITB12\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 22.816000000000003,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 380.2666666666667,\n" +
                "        \"unit\" : \"µg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Vitamin D\",\n" +
                "        \"tag\" : \"VITD\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 14.720000000000002,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 3.6800000000000006,\n" +
                "        \"unit\" : \"µg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Vitamin E\",\n" +
                "        \"tag\" : \"TOCPHA\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 38.37765785972438,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 191.8882892986219,\n" +
                "        \"unit\" : \"mg\"\n" +
                "      }, {\n" +
                "        \"label\" : \"Vitamin K\",\n" +
                "        \"tag\" : \"VITK1\",\n" +
                "        \"schemaOrgTag\" : null,\n" +
                "        \"total\" : 191.96554028957547,\n" +
                "        \"hasRDI\" : true,\n" +
                "        \"daily\" : 239.95692536196935,\n" +
                "        \"unit\" : \"µg\"\n" +
                "      } ]\n" +
                "    },\n" +
                "    \"bookmarked\" : false,\n" +
                "    \"bought\" : false\n" +
                "  }]}";
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    static public boolean isNetworkAvailable(Context c) {
        ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }
}
