if(rshasgrafics.getBoolean("hasgrafics")){
        System.out.println("The system you are designing have onboard grafics, do you want to install a one anyway? y/n");
        while(keeprun==true){
        Scanner menu=new Scanner(System.in);
        String menupick=menu.next();
        // compares input from system in to the cases that repesent a case in use.
        switch(menupick){
        case"y":case"yes":
        hasgrafics=true;keeprun=false;break;
        case"n":case"no":
        hasgrafics=false;keeprun=false;break;
        default:
        System.out.println("Invalid choice, please pick again.");
        break;}}
        }else{
        hasgrafics=true;
        }