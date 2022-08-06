```
# AtlaSoftware
VC Pentru proiectul colectiv "AtlaSoftware"

Working language for all code and comments is english
## RULES FOR WRITING CODE
(these rules are subject to change and should be re-read periodically)

  Naming conventions:
      -all names should be written with the camelCaseStandard
      -all class names should start with a CapitalLetter
      -all classes, functions, and variables should be named *VERY* descriptively (so as to understand what the code is at a glance)
      -all package/module names should be written with a lowercase letter, with multiple words separated by "." and they should be named descriptively
      -all branches should be named starting with the issue code/name and should describe the task succintly
          - BE - sequential number of task - name of task
          - FE - sequential number of task - name of task
          - TE - sequential number of task - name of task
          
          BE = Back-End
          FE = Front-End
          TE = Testing
          
          
      -all UnitTest classes should start with the name of the class being tested and end with the word *Test*
      -all functional testing classes should be named with the functionality being tested and end with the words *FunctionalTest*
  Coding conventions:
       -***ABSOLUTELY NO SPAGHETTI CODE WHATSOEVER***
      -optional: install SonarLint for your IDE
      -maximum line length <95 characters
      -all code *MUST* be thoroughly commented with the standard described below:
        -for methods:
          /**
            Input:  (what parameters does the method take)
            Output: (what does the method return)
            Description:  (short description of what the method does)
          /
          (or whatever the multi-line comment in the language we'll use is)
        -for classes:
          //Short description of what the class' purpose is, and where it is used
        -for variables:
          //Short description of what the variable's purpose is if it is not self-explanatory
      -all methods and classes should follow SOLID principles and should not break Encapsulation
      -all repository classes should be in their own package and should be named accordingly, ending with the word *Repository*
      -all domain classes should be in their own package and should be named accordingly
      -all test classes should be in the testing folder generated automatically by the IDE
      -all indentation should be done with TAB and not with SPACE
      -feel free to use the automatic code formatter in the IDE
      -all code should be reviewed for typos and convention violations BEFORE being pushed
      -all code should be summarily checked to see if it compiles properly before being pushed
  Git conventions:
      -the length of commit message titles should be <75 characters long
      -all code pertaining to a certain task should be written in its own separate branch
      -all commit messages should start with the code of the task and a short but descriptive message of the task and should be <75 characters long per line
          example: BE-1-description
      -all separate branches should start with the code of the task and the name of the task
      -CODE REVIEWERS FOR EACH DEPARTMENT:
        -BE- Capan Matei, Apetri Stefan, Lazar Alexandru
        -FE- Magherusan Alexandru, Ciubuca Adrian, Crisan Tudor
        -TE- Crisan Tudor, Capan Matei 
      -ASSIGN A CODE REVIEWER FROM THE ABOVE LIST WHEN YOU CREATE A PULL REQUEST
      -all code *MUST* be reviewed before being merged onto the develop branch
      -it is the responsibility of the developer not to create conflicts with the other branches and to keep their local repository up to date with remote
      -update your repository once every two days, or after a branch has been merged onto develop
      -all branches should be based on develop, not main
      -the main branch is for the STABLE, WORKING product
      
      ```
