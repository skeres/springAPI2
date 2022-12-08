package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


@RepositoryRestResource        //Dit à Spring que les méthodes ci dessous doivent etre accessible
                               //via une API rest. Il faut donc définir la base de données
 public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {

    // JpaRepository est de type <T, ID>
    //Spring va générer automatiquement des recherches "standards" sur la classe Etudiant
    // exemple : reherche sur les id, etc ...

    //Pour implénenter des méthodes spécifiques, 2 possibilités

    //1 : utiliser les conventions de nommage. Grace à cela, Spring va automatiquement générer les méthodes adhocs.
    //find = select
    //By = clause where
    //Nom = attribut de la classe
    //Contains = clause like
    //Startwith = clause startwith
    //exemple de requete "pré-implémentée" :
    public List<Etudiant> findByNom(String nom);
    public List<Etudiant> findByNomContains(String valeur);

    //2 : implémenter un requete spécifique
    // exemple de requete spécifique implémentée
    //langage HQL ( hibernate )
    @Query("select e from Etudiant e where e.nom like :x ")  // x est le paramètre
    public List<Etudiant> mySpecificQuery(@Param("x") String nom); // Param créé un lien entre le paramètre Query et la signature de la méthode

   /* exemple de requetes http possibles
   http://localhost:8081/etudiants  sort by [id, descending] (default) & pagination [page=0, size=3] (default)
   http://localhost:8081/etudiants?sort=prenom,asc sort by [prenom, ascending] & pagination [page=0, size=3] (default)
   http://localhost:8081/etudiants?sort=prenom,asc&sort=nom,desc sort by [prenom, ascending] & sort by [nom, desc] & pagination [page=0, size=3] (default)


    */
}
