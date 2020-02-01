# avazdusevich-price-calc
maven project

learning how to work with git and make Maven project

    1. Работа в терминале:
    
        для просмотра списка папок в текущей директории используй: ll
        для перемещения между директориями используй: cd directoryName
        для создания новой папки используй: mkdir directoryName

    2. Создание нового репозитория на git-е:

    2.1 
        - new; 
        - Repository name; 
        - Description (optional); 
        - Public; 
        - проставляем галочку в чек-боксе "Initialize this repository with a README" 
        - Add .gitignore: Java; 
        - Add a license: Apache License 2.0 (если я все правильно понял, то данная лицензия применяется т.к. мы используем Maven) 
        - Create repository

    2.2 - на просторах интернета ищем "gitignore для java"; 
        - добавляем в наш .gitIgnore блок с исключениями для IntellijIDEA: 
        
            ##############################
            ## IntelliJ
            ##############################
            out/
            .idea/
            .idea_modules/
            *.iml
            *.ipr
            *.iws

    3. Клонируем получившийся репозиторий на диск:
        - Clone or download;
        - copy URL;
        - в терминале: git clone URL

    4. Собираем проект с помощью Maven:
        mvn archetype:generate
        2501
        Define value for property 'groupId': com.epam
        Define value for property 'artifactId': avazdusevich-price-calc [ avazdusevich-цена-известково]
        Define value for property 'version' 1.0-SNAPSHOT: : 1.2
        Define value for property 'package' com.epam: :
        Define value for property 'compilerMode' simple: :
        Define value for property 'testLibrary' junit: :

    5. Импортируем проект в IDEA:
        ImportProject;
        Select directory;
        Import Project from external model
        Maven
        Finish

    6. Создаем main.class и коммитим (можно делать коммит напрямую из Идеи, можно с помощью терминала)

Для работы с GIT-ом через терминал: 

    - git status - проверяем, на какой ветке находимся и какие имеются изменения 
    - git add . - добавляем все изменения для коммита 
    - git commit -m "" - создаем коммит с комментарием 
    - git push - загружаем коммит в репозиторий.