# SamVa

Ce projet est une maquette d'application Android (Java only sans Kotlin) à but éducatif.

SamVa est la première app qui te permet de trouver tous les évènement de PACA et un Sam pour ta soirée.
Données des événements extraites d'une base openData : https://public.opendatasoft.com/explore/dataset/evenements-publics-cibul/ 

Les premières données affichées dans la liste sont les événements en PACA. Une fois un événement sélectionné on accède aux détails de celui-ci ainsi qu'aux différents "Sam" s'étant déjà désignées comme tel. 
Un bouton permet de se rajouter comme Sam (conducteur allant à l'événement). On peut également cliquer sur un Sam présent pour afficher ses détails et se rajouter comme passager. Les passagers enregistrés ne sont pas affichés publiquement mais influent sur les places restantes à bord de la voiture.

Cette alpha utilise des bibliothèques externes telles que : play-services-maps android-maps-utils retrofit2 gson et converter-gson cardview firebase
