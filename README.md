TASK:
Du ska skapa ett Git-repository på GitHub och göra en commit för varje ändring du anser tillför något till koden. Du ska ge dina commits talande namn (t.ex. “Made buying animals work”).

Namn på commits, samt kommentarer du skriver i din kod ska vara på engelska.

Spelet kan prata engelska eller svenska i sitt användargränssnitt/med spelaren, men välj ett av dessa språk.

Skapa ett Java-program i form av en Command Line App (dvs. terminalen är användargränssnitt för in- och utmatning). Programmet ska vara ett spel som fungerar enligt nedanstående beskrivning.

Klasser du ska ha med i din lösning: Game, Player, Store (som man köper och säljer djur till och köper mat ifrån), Animal (abstrakt), Game.Food (abstrakt), samt minst 5 olika klasser av djur som alla ärver från Animal, samt minst 3 olika klasser av mat som alla ärver från Game.Food.

Spelare ska ha namn och pengar som egenskaper/fält. Djur ska namn, kön och hälsa som egenskaper. Vilka egenskaper/fält (på Player, Animal och övriga klasser), samt metoder som behövs i övrigt får du fundera över och avgöra själv.

Spelgång och spelregler (G-nivå):

    När spelet startas anger man hur många rundor man vill spela (5-30), samt hur många spelare (1-4 som sitter vid samma dator som ska delta)
    En spelare påbörjar spelet med en viss summa pengar (samma för alla spelare, du bestämmer hur mycket) och äger då inga djur.
    En spelare kan köpa olika slags djur (och då välja typ och kön, samt döpa djuret) Det ska gå att välja mellan minst 5 olika sorter. Varje runda/spelomgång kan en spelare välja ett av följande alternativ:
    a) Köpa max så många djur som hen har pengar till (varje typ av djur har ett fast ursprungspris, oavsett kön)
    b) Köpa max så mycket mat som hen har pengar till (mat köps i kg och har kilopris)
    c) Mata sina djur (vilken slags mat måste anges för varje djur man vill mata)
    d) Försöka få ett par djur att para sig, då skapas i 50% av fallen nya djur man äger (om djuren är av samma slag och olika kön, olka slags djur kan inte para sig). Om parningen lyckas kan spelaren döpa det/de nya djuret/djuren (olika slags djur kan ha olika många ungar/parning). Könet på djuren som skapas vid parning slumpas (50% hona, 50% hane).
    e) Sälja ett-flera djur (priset är ursprungspriset gånger hälsovärdet)
    När en spelare har gjort sina val är det dags för nästa spelare att göra sina val.
    Djur har ett hälsovärde som från början är 100 procent. För varje spelomgång sjunker detta värde med 10 - 30 procentenheter (slumpa detta tal).
    Om ett djur har hälsovärde 0 dör det. Meddela spelaren som äger djuret detta först i nästa runda.
    Om man matar ett djur stiger dess hälsovärde - varje kg mat förbättrar djurets hälsa med 10 procent. (Om du vill får du lova att sätta olika djurs matbehov för att förbättra sin hälsa 10 procent till olika vikter av mat… Det kan ju vara rimligt att en mus behöver mindre mat än en elefant för att må bra?)
    Ett viss typ av djur kan bara äta 1-3 av de sorters mat som finns i spelet. Att mata djuret med ett annat slags mat går inte. (Det får inte vara så att det finns ett slags mat som alla typer av djur kan äta.)
    En spelare förlorar och lämnar spelet när spelaren inte har några pengar och inte har några djur.
    Efter sista rundan säljs alla djur och spelaren får pengarna.
    Den spelare som har mest pengar efter sista rundan vinner.
    Se till att varje spelare tydligt i början av varje runda få information om vilka djur hen äger, vilken mat hen äger och hur mycket pengar hen har, samt hur mycket djurs hälsa har försämrats sedan förra omgången.
    Man kan inte sälja mat.
    Man köper och säljer djur från en affären av klassen Store.
    Man köper mat från en affär av klassen Store (kan vara samma affär som den man köper och säljer från djur från).


Extra genomför två av följande fyra punkter:

    Djur kan bli sjuka (20% chans per djur och spelrunda). I så fall måste man betala veterinärkostnader för dem (olika pris för olika slags djur). Då är det 50% chans att de blir friska. Annars dör de.
    Man kan spara pågående spel till disk och fortsätta det senare. Flera spel kan sparas under olika filnamn. Användaren väljer filnamn.
    Man kan köpa och sälja djur till varandra (mellan olika spelare).
    Varje djurtyp har en maxålder och varje djur blir äldre för varje omgång. Åldern påverkar priset på djuret när man säljer det negativt. Ett djur som blivit äldre än sin maxålder dör.
