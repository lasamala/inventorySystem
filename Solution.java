{\rtf1\ansi\ansicpg1252\cocoartf2513
\cocoatextscaling0\cocoaplatform0{\fonttbl\f0\fswiss\fcharset0 Helvetica;}
{\colortbl;\red255\green255\blue255;}
{\*\expandedcolortbl;;}
\margl1440\margr1440\vieww15180\viewh13120\viewkind0
\pard\tx720\tx1440\tx2160\tx2880\tx3600\tx4320\tx5040\tx5760\tx6480\tx7200\tx7920\tx8640\pardirnatural\partightenfactor0

\f0\fs36 \cf0 \
//import the required packages such as io and util\
import java.io.*;\
import java.util.*;\
\
public class Solution\{\
    \
    // global declaration of the inventory system to fetch the data and update the data from any method\
    public static TreeMap<String,Map<String,Integer>> inventory = new TreeMap();\
\
    public static void main(String []args)\{\
        //fill in data into the inventory\
        fillDB();\
\
        //list of items to place order along with its quantity.\
        TreeMap<String,Integer> cart = new TreeMap();\
        cart.put("apple",5);\
        cart.put("banana",5);\
        cart.put("orange",10);\
\
        //calling the placeOrder method to fetch the list of stores that contains the required items\
        Map<String,Map<String,Integer>> summary = placeOrder(cart);\
        \
        //displaying the result\
        System.out.println(summary);\
     \}\
\
    //filling the inventory one store at a time\
    public static void fillDB()\{\
\
         TreeMap<String,Integer> store1 = new TreeMap<String,Integer>();\
         store1.put("apple",5);\
         store1.put("orange",10);\
         inventory.put("owd", store1);\
\
         TreeMap<String,Integer> store2 = new TreeMap<String,Integer>();\
         store2.put("banana",5);\
         store2.put("orange",10);\
         inventory.put("dm",store2);\
     \}\
\
    public static Map<String,Map<String,Integer>> placeOrder(TreeMap<String,Integer> cart)\{\
        \
	//initializing the order summary\
    Map<String,Map<String,Integer>> summary = new TreeMap();\
    \
    //check the base case when there are no items in the cart    \
    if(cart == null || cart.size() < 1)\
            return summary;\
    \
    //iterating over the db to check which store contains what items\
	for(String key : inventory.keySet())\{\
            \
            //for each store fetch the list of items along with its quantity\
            Map<String,Integer> items = inventory.get(key);\
            \
            //initialize the list of items system will pick from this store\
            TreeMap<String,Integer> res = new TreeMap();\
            \
            //iterate through every item in the cart to fullfill it \
            for(Map.Entry<String,Integer> et : cart.entrySet())\{\
                \
                //if this store contians the item pick the item from this store\
                if(items.containsKey(et.getKey()))\{\
                    int value = items.get(et.getKey());\
                    \
                    //check if the item has any quantity or did we already used everything up\
                    if( value == 0 || et.getValue() == 0 )\
                        continue;\
                    \
                    /*if the quantity user is seeking for is less than or equal to quantity in the inventory than update the inventory, the resultant summary and the cart list */\
                    if( value >= et.getValue() )\{\
                            items.put(et.getKey(),value-et.getValue());\
                            res.put(et.getKey(),et.getValue());\
                            /*we fullfilled the entire request for this particular product in the current store */\
                            cart.put(et.getKey(),0); \
                    \}\
                    /*if the quantity user is seeking for is greater than the quantity in the inventory than update the inventory, the resultant summary and cart list */\
                    else\{\
                        int updateValue = et.getValue()-value;\
                        res.put(et.getKey(),updateValue);\
                        /*we have used up entire product at this particular store for the current order */\
                        items.put(et.getKey(),0); \
                        cart.put(et.getKey(),updateValue);\
                    \}\
                    \
                \}\
            \}\
            //update the inventory\
            inventory.put(key,items);\
            \
            //update the order summary\
            summary.put(key,res);\
        \}\
        return summary;\
    \}\
\}}