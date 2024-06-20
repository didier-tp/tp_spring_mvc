CONVENTIONS d'URL (pour cette application):
==========================================

/site/...         pour partie "Dynamic HTML" (jsp ou thymeleaf) 
/rest/api-xyz/... pour partie api-REST
/... (autre ex: /index.html , .css , .js) pour partie statique

Ceci permettra un bon réglage de la sécurité .

ORGANISATION DES REPERTOIRES:
============================


dans src/main/resources:

/static 
    pour toute la partie statique (.html , .css , .js) 
    interprétée coté navigateur seulement
    
/templates
    pour la partie "thymeleaf"
    
pour la partie "jsp" (assez ancienne):

Si pas springBoot et ".war" alors
/src/main/webapp
     WEB-INF prévu pour xyz.jsp
     
Si springBoot (souvent .jar) alors (dans src/main/resources)

/views
   xyz.jsp
   
avec dans application.properties
spring.mvc.view.prefix=/views/
spring.mvc.view.suffix=.jsp

============
BUG temporaire (à résoudre) , en version très récente (spring 6 + Spring booot 3 + Spring Security )
la protection CSRF semble ne pas bien fonctionner !!!!
