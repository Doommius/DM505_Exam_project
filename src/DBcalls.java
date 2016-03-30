import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

//java -cp postgresql-9.4-1201.jdbc4.jar:. DBtest

public class DBcalls {

    public static void main(String[] args) {
    }

    private static String checktable(String Part_ID) {
        if (Part_ID.contains("CPU-")) {
            return "cpu";
        } else if (Part_ID.contains("HDD-")) {
            return "Storage";
        } else if (Part_ID.contains("MB-")) {
            return "motherboard";
        } else if (Part_ID.contains("RAM-")) {
            return "ram";
        } else if (Part_ID.contains("GFX-")) {
            return "graphics";
        } else if (Part_ID.contains("SYS-")) {
            return "computer";
        } else if (Part_ID.contains("CASE-")) {
            return "computercase";
        } else {
            System.out.println("Part or system not listed in database");
            return null;
        }


    }

    public static void Printallparts(Connection con) {
        //Prints all parts in system.
        List<String> allpartsquery = new ArrayList<String>();
        allpartsquery.add("SELECT cpu.model, cpu.stock FROM public.cpu;");
        allpartsquery.add("SELECT graphics.model, graphics.stock FROM public.graphics;");
        allpartsquery.add("SELECT ram.model, ram.stock FROM public.ram;");
        allpartsquery.add("SELECT motherboard.model, motherboard.stock FROM public.motherboard;");
        allpartsquery.add("SELECT storage.model, storage.stock FROM public.storage;");
        System.out.println("Model" + "                          |" + " Stock");
        for (String query : allpartsquery) {

            try {
                Statement st = con.createStatement();


                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String model = rs.getString("model");
                    int stock = rs.getInt("stock");
                    System.out.println(model + " | " + stock);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Sellitem(Connection con) {
        //sells an item.
        String Part_ID = null;
        String Table_ID = null;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the model ID for the part you wish to sell.");
        Part_ID = keyboard.next();
        Table_ID = checktable(Part_ID);
        if (Table_ID == null) {
            return;
        } else if (Table_ID == "computer") {
            sellsystem(con, Part_ID);
            return;
        }
        try {
            Statement st = con.createStatement();
            String query = "UPDATE " + Table_ID + " SET Stock = Stock-1 WHERE model SIMILAR  TO '%" + Part_ID + "%';";
            System.out.println("sold " + Part_ID);
            st.executeUpdate(query);
            System.out.println("Sold 1 x " + Part_ID);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void sellsystem(Connection con, String system) {
        //calculates the price of a system.
        ArrayList<String> syspartlist = new ArrayList<String>();
        int price = 0;
        String Table_ID = null;
        try {
            Statement st = con.createStatement();
            String query = "Select * FROM  computer  WHERE model SIMILAR  TO '%" + system + "%';";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            syspartlist.add(rs.getString("cpu"));
            syspartlist.add(rs.getString("ram"));
            syspartlist.add(rs.getString("Storage"));
            syspartlist.add(rs.getString("Motherboard"));
            syspartlist.add(rs.getString("computercase"));
            syspartlist.add(rs.getString("graphics"));
            System.out.println(syspartlist.toString());
            for (String Part_ID : syspartlist) {
                // System.out.println(Part_ID);
                if (Part_ID != null) {
                    Table_ID = checktable(Part_ID);
                    try {
                        query = "UPDATE " + Table_ID + " SET Stock = Stock-1 WHERE model SIMILAR  TO '%" + Part_ID + "%';";
                        System.out.println("sold " + Part_ID);
                        st.executeUpdate(query);

                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("there was a problem with a Part");
                    }
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("there was a problem with a system");
        }

        System.out.print(price);

    }

    public static void Priceoffer(Connection con) {
        //returns price for parts and systems.
        String Part_ID = null;
        String Table_ID = null;
        int multiplier = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the model ID for the part you wish to get a price offer..");
        Part_ID = keyboard.next();
        Table_ID = checktable(Part_ID);
        if (Table_ID == null) {
            return;
        } else if (Table_ID == "computer") {
            System.out.println("Enter the multiplier for how many systems you would like to buy..");
            Scanner multiplierinput = new Scanner(System.in);
            multiplier = multiplierinput.nextInt();
            multiplier -= 1;
            double pricemultiplier = multiplier * 2;
            if (pricemultiplier > 20) {
                pricemultiplier = 20;
            }
            Double systemprice = (((((systemprice(con, Part_ID)) * 13 / 10) / 100) * 100 + 99) * (1 + multiplier) * (1 - (pricemultiplier / 100)));
            int finalprice = systemprice.intValue();
            System.out.println("Price offer for " + Part_ID + " is " + finalprice);
            return;
        }
        try {
            Statement st = con.createStatement();
            String query = "Select price FROM " + Table_ID + " WHERE model SIMILAR  TO '%" + Part_ID + "%';";
            ;
            ResultSet rs = st.executeQuery(query);
            rs.next();

            int price = ((rs.getInt("price") * 13 / 10));
            System.out.println("Price offer " +
                    "for " + Part_ID + " is " + price);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void customsystem(Connection con) {
        try {
            ArrayList<String> syspartlist = new ArrayList<String>();
            String Part_MB,Part_CPU,Part_RAM,Part_GPU,Part_Storage,Part_case;
            String query;
            Statement st = con.createStatement();


            System.out.println("You can use the following Storage for this sysyem");
            query = "Select model from cpu;";
            ResultSet rscpu = st.executeQuery(query);
            int columns = rscpu.getMetaData().getColumnCount();
            while (rscpu.next()) {
                System.out.print(rscpu.getString(columns));
            }
            System.out.println();
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the CPU that you wish to use.");
            Part_CPU = keyboard.next();
            syspartlist.add(Part_CPU);



            System.out.println("You can use the following motherboards for this CPU");
            query = "Select model from motherboard where motherboard.socket = (Select socket from cpu where model SIMILAR TO '%" + Part_CPU + "%');";
            ResultSet rs = st.executeQuery(query);
            columns = rs.getMetaData().getColumnCount();
            ArrayList<String> motherboards = new ArrayList<String>();
            while (rs.next()) {
                System.out.print(rs.getString(columns));
            }
            System.out.println();
            System.out.println("Enter the Model for the motherboard that you wish to use.");
            Part_MB = keyboard.next();
            syspartlist.add(Part_MB);



            System.out.println("You can use the following Ram for this system");
            query = "Select model from ram where (ram.ramtype = (Select ramtype from motherboard where model SIMILAR TO '%" + Part_MB + "%')AND ram.fsb = (Select fsb from cpu where model Similar to '%" + Part_CPU + "%'));";
            ResultSet rsram = st.executeQuery(query);
            columns = rsram.getMetaData().getColumnCount();
            ArrayList<String> ram = new ArrayList<String>();
            while (rsram.next()) {
                System.out.print(rsram.getString(columns));
            }
             System.out.println();
            System.out.println("Enter the Model for the Ram that you wish to use.");
            Part_RAM = keyboard.next();
            syspartlist.add(Part_RAM);



            System.out.println("You can use the following Storage for this sysyem");
            query = "Select model from storage;";
            ResultSet rsstorage = st.executeQuery(query);
            columns = rsstorage.getMetaData().getColumnCount();
            while (rsstorage.next()) {
                System.out.print(rsstorage.getString(columns));
            }
            System.out.println();
            System.out.println("Enter the Model for the Storage drive that you wish to use.");
            Part_Storage = keyboard.next();
            syspartlist.add(Part_Storage);



            query = "Select hasgrafics From cpu where model similar to '%" + Part_CPU + "%');";
            ResultSet rshasgrafics = st.executeQuery(query);
            rshasgrafics.next();
            if (rshasgrafics.getBoolean("hasgrafics")){
                System.out.println("The system you are designing have onboard grafics, do you want to install a one anyway?");
            }


//                System.out.print(rsstorage.getString(columns));
//            }


//            System.out.println("You can use the following Storage for this sysyem");
//            query = "Select model from storage);";
//            ResultSet rsstorage = st.executeQuery(query);
//            columns = rsstorage.getMetaData().getColumnCount();
//            while (rsstorage.next()) {
//                System.out.print(rsstorage.getString(columns));
//            }
            syspartlist.forEach(s -> System.out.println(s));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static boolean checkpart(Connection con) {
return false;
    }

    private static int systemprice(Connection con, String system) {
        //calculates the price of a system.
        ArrayList<String> syspartlist = new ArrayList<String>();
        int price = 0;
        String Table_ID = null;
        try {
            Statement st = con.createStatement();
            String query = "Select * FROM  computer  WHERE model SIMILAR  TO '%" + system + "%';";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            syspartlist.add(rs.getString("cpu"));
            syspartlist.add(rs.getString("ram"));
            syspartlist.add(rs.getString("Storage"));
            syspartlist.add(rs.getString("Motherboard"));
            syspartlist.add(rs.getString("computercase"));
            syspartlist.add(rs.getString("graphics"));
            for (String Part_ID : syspartlist) {
                // System.out.println(Part_ID);
                if (Part_ID != null) {
                    Table_ID = checktable(Part_ID);
                    try {
                        query = "Select price FROM  " + Table_ID + "  WHERE model SIMILAR  TO '%" + Part_ID + "%';";
                        rs = st.executeQuery(query);
                        rs.next();
                        int tempvalue = rs.getInt("price");
                        ;
                        price += tempvalue;

                    } catch (SQLException e) {
                        e.printStackTrace();
                        System.out.println("there was a problem with a Part");
                    }
                }
            }
            return price;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("there was a problem with a system");
        }

        System.out.print(price);
        return price;
    }

    public static void Printallpartswithprice(Connection con) {
        //Prints all parts with price.
        List<String> allpartsquery = new ArrayList<String>();
        allpartsquery.add("SELECT cpu.model, cpu.price FROM public.cpu;");
        allpartsquery.add("SELECT graphics.model, graphics.price FROM public.graphics;");
        allpartsquery.add("SELECT ram.model, ram.price FROM public.ram;");
        allpartsquery.add("SELECT motherboard.model, motherboard.price FROM public.motherboard;");
        allpartsquery.add("SELECT storage.model, storage.price FROM public.storage;");
        allpartsquery.add("SELECT computercase.model, computercase.price FROM public.computercase;");
        System.out.println("Model" + "                          |" + " Price ");
        for (String query : allpartsquery) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String model = rs.getString("model");
                    int price = rs.getInt("price");
                    System.out.println(model + " | " + price);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void listsystems(Connection con) {
        //list all systems and their prices.
        String query = "Select * from Computer";
        System.out.println("Model                         name                          build cost     price offer");
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            int columns = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                String model = rs.getString("model");
                System.out.print(model);
                System.out.print(rs.getString("name"));
                System.out.print(systemprice(con, model));
                System.out.print("           ");
                System.out.print((((systemprice(con, model)) * 13 / 10) / 100) * 100 + 99);
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void Restockinglist(Connection con) {
        //Prints a list of things to restock.
        List<String> allpartsquery = new ArrayList<String>();
        allpartsquery.add("SELECT cpu.model, cpu.stock, cpu.refillstock FROM public.cpu;");
        allpartsquery.add("SELECT graphics.model, graphics.stock, graphics.refillstock FROM public.graphics;");
        allpartsquery.add("SELECT ram.model, ram.stock, ram.refillstock FROM public.ram;");
        allpartsquery.add("SELECT motherboard.model, motherboard.stock, motherboard.refillstock FROM public.motherboard;");
        allpartsquery.add("SELECT storage.model, storage.stock, storage.refillstock FROM public.storage;");
        allpartsquery.add("SELECT computercase.model, computercase.stock, computercase.refillstock FROM public.computercase;");
        System.out.println("Model                          | In Stock | preferred level | restocking");
        for (String query : allpartsquery) {

            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                String Table_ID;
                while (rs.next()) {
                    String model = rs.getString("model");
                    int stock = rs.getInt("stock");
                    int restock = rs.getInt("refillstock");
                    if (stock < restock) {
                        System.out.println(model + " | " + stock + " | " + restock + " | " + (restock - stock));
                    }

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void Restock(Connection con) {
        //restocks the Stock.
        List<String> allpartsquery = new ArrayList<String>();
        ArrayList<String> Restocklist = new ArrayList<String>();
        String Table_ID;
        allpartsquery.add("SELECT cpu.model, cpu.stock, cpu.refillstock FROM public.cpu;");
        allpartsquery.add("SELECT graphics.model, graphics.stock, graphics.refillstock FROM public.graphics;");
        allpartsquery.add("SELECT ram.model, ram.stock, ram.refillstock FROM public.ram;");
        allpartsquery.add("SELECT motherboard.model, motherboard.stock, motherboard.refillstock FROM public.motherboard;");
        allpartsquery.add("SELECT storage.model, storage.stock, storage.refillstock FROM public.storage;");
        allpartsquery.add("SELECT computercase.model, computercase.stock, computercase.refillstock FROM public.computercase;");
        System.out.println("Model                          | In Stock | preferred level | restocking");
        for (String query : allpartsquery) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    String model = rs.getString("model");
                    int stock = rs.getInt("stock");
                    int restock = rs.getInt("refillstock");
                    if (stock < restock) {
                        System.out.println(model + " | " + stock + " | " + restock + " | " + (restock - stock));
                        Table_ID = checktable(model);
                        Restocklist.add("UPDATE " + Table_ID + " SET stock = refillstock WHERE model SIMILAR  TO '%" + model + "%';");
                    }
                }
                for (String updatequery : Restocklist) {
                    st.executeUpdate(updatequery);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}