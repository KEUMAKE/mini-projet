abstract class Employe {
    private  String Matricule;
    private  String Nom;
    private  String Prenom; 
    private  int Age;
    private  String Date_prise_service;
    
    
    public Employe (String M,String N,String P,int A,String D )
    {
        this.Matricule=M;
        this.Nom = N;
        this.Prenom = P;
        this.Age = A;
        this.Date_prise_service = D;
    }
    
    public abstract double calculerSalaire();
    
    public String getTitre()
    {
        return "L'employe " ;
    }
    
    public String getNom() {
        return getTitre() + Matricule + Prenom +" " + Nom;
    }
}

abstract class Commercial extends Employe {
    private final double chiffreAffaire;
    
    public Commercial(String Matricule,String Prenom, String Nom, int Age, String Date,
            double chiffreAffaire) {
        super(Matricule,Prenom, Nom, Age, Date);
        this.chiffreAffaire = chiffreAffaire;
    }
    
    public double getChiffreAffaire()
    {
        return chiffreAffaire;
    }
    
}


class Vendeur extends Commercial {
    private final static double POURCENTAGE_VENDEUR = 0.2;
    private final static int BONUS_VENDEUR = 100;
    public Vendeur( String Matricule,String Prenom, String Nom, int Age, String Date,
            double chiffreAffaire) {
        super(Matricule,Prenom, Nom, Age, Date, chiffreAffaire);
    }
    
   @Override
    public double calculerSalaire() {
        return (POURCENTAGE_VENDEUR * getChiffreAffaire()) + BONUS_VENDEUR;
    }
    
    @Override
    public String getTitre()
    {
        return "Le vendeur ";
    }
    
}


class Representant extends Commercial {
    private final static double POURCENT_REPRESENTANT = 0.2;
    private final static int BONUS_REPRESENTANT = 200;
    public Representant(String Matricule,String Prenom, String Nom, int Age, String Date, double chiffreAffaire) {
        super(Matricule,Prenom, Nom, Age, Date, chiffreAffaire);
    }
    
    @Override
    public double calculerSalaire() {
        return (POURCENT_REPRESENTANT * getChiffreAffaire()) + BONUS_REPRESENTANT;
    }
    
    @Override
    public String getTitre()
    {
        return "Le reprÃ©sentant ";
    }
}

class Technicien extends Employe {
    private final static double FACTEUR_UNITE = 5.0;
    
    
    private final int unites;
    
    public Technicien(String Matricule,String Prenom, String Nom, int Age, String Date, int unites) {
        super(Matricule,Prenom, Nom, Age, Date);
        this.unites = unites;
    }
    
    @Override
    public double calculerSalaire() {
        return FACTEUR_UNITE * unites;
    }
    
    @Override
    public String getTitre()
    {
        return "Mon technicien";
    }
}

class Manutentionnaire extends Employe {
    private final static double SALAIRE_HORAIRE = 65.0;
    private final int heures;
    
    public Manutentionnaire(String Matricule,String Prenom, String Nom, int Age, String Date,
            int heures) {
        super(Matricule,Prenom, Nom, Age, Date);
        this.heures = heures;
    }
    
    @Override
    public double calculerSalaire() {
        return SALAIRE_HORAIRE * heures;
    }
    
    @Override
    public String getTitre()
    {
        return "Le Manutention. " ;
    }
}


interface Risque {
    int PRIME = 25000;
}


class TechnARisque extends Technicien implements Risque {
    
    public TechnARisque( String Matricule,String Prenom, String Nom, int Age, String Date, int unites) {
        super(Matricule,Prenom, Nom, Age, Date, unites);
    }
    
    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}


class ManutARisque extends Manutentionnaire implements Risque {
    
    public ManutARisque(String Matricule,String Prenom, String Nom, int Age, String Date, int heures) {
        super( Matricule,Prenom, Nom, Age, Date, heures);
    }
    
    @Override
    public double calculerSalaire() {
        return super.calculerSalaire() + PRIME;
    }
}


class Personnel {
    private final Employe[] staff;
    private int nombreEmploye;
    private final static int MAXEMPLOYE = 200;
    
    public Personnel() {
        staff = new Employe[MAXEMPLOYE];
        nombreEmploye = 0;
    }
    
    public void ajouterEmploye(Employe e) {
        ++nombreEmploye;
        if (nombreEmploye <= MAXEMPLOYE) {
            staff[nombreEmploye - 1] = e;
        } else {
            System.out.println("Pas plus de " + MAXEMPLOYE + " employe");
        }
    }
    
    public double salaireMoyen() {
        double somme = 0.0;
        for (int i = 0; i < nombreEmploye; i++) {
            somme += staff[i].calculerSalaire();
        }
        return somme / nombreEmploye;
    }
    
    public void afficherSalaires() {
        for (int i = 0; i < nombreEmploye; i++) {
            System.out.println(staff[i].getNom() + " gagne "
                    + staff[i].calculerSalaire() + " francs.");
        }
    }
}


class Salaires {
    public static void main(String[] args) {
        Personnel p = new Personnel();
        pers.ajouterEmploye(new Vendeur(" 2AO ", " YVES ", " ROBERT ", 12, " 2013 ", 30000));
        pers.ajouterEmploye(new Representateur(" 3AO ", " AISSATOU ", " ALEXIE ", 29, " 2016 ", 20000));
        pers.ajouterEmploye(new Producteur(" 4AO ", " ERIC ", " BELLO ", 30, " 1995 ", 1000));
        pers.ajouterEmploye(new Manutentionnaire(" 5AO ", " AMINATOU ", " POISSON ", 32, " 1999 ", 45));
        pers.ajouterEmploye(new ProducteurRisque(" 6AO ", " INA ", " MOUTON ", 47, " 2000 ", 1000));
        pers.ajouterEmploye(new ManutentionnaireRisque(" 7AO ", " LTICIA ", " BELIER", 29, " 2016 ", 45));
        
        p.afficherSalaires();
        System.out.println("Le salaire moyen dans l'entreprise est de "
                + p.salaireMoyen() + " francs.");
        
    }
    
}