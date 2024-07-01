NB:

au sein de pom.xml la dépendance suivante:
                <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
		</dependency>

permet un redémarrage automatique de l'application springBoot 
dès que l'on met à jour le code java.

Ceci fonctionne généralement bien avec eclipse (où il faut déclencher explicitement "save" ou "Ctrl-s" pour sauvegarder et compiler le code"
Certaines versions "ultimate" de intelliJ se débrouillent bien également.
Avec "IntelliJ community" , on peut activer (dans "settings") les réglages suivants:
  - Build, Execution, Deployment -> Compiler: Cocher "Build project automatically"
  - Advanced Settings: cocher "Allow auto-make to start even if developed application is currently running"


==============

pour "skipTests" sous intelliJ , cliquer sur l'icone ressemblant un peu à un  "sens interdit" 
