while(keeprun==true){
        Scanner menu=new Scanner(System.in);
        String menupick=menu.next();

        switch(menupick){
        case"Listall":case"listall":case"la":case"LA":
        DBcalls.Printallparts(con);
        break;
        case"Listallsystems":case"LAS":case"las":case"listallsystems":
        DBcalls.listsystems(con);
        break;
        };
        }