# Kotlin RPN Notation Parser

### Obiettivo

Sviluppare un programma per il parsing di semplici espressioni matematiche 
espresse in [Notazione Polacca Inversa (RPN)](https://it.wikipedia.org/wiki/Notazione_polacca_inversa).    

### Startup

Creare progetto IntelliJ con supporto a Gradle e Kotlin.    
Aggiungere nella closure `dependencies` del file `build.gradle` la seguente stringa:    
```
testImplementation "junit:junit:4.12"
```

Gli unit test sono i benvenuti! 😉

### Requisiti 
  
Le espressioni da parsare saranno delimitate da parentesi tonde e, per semplicità, ogni elemento
dell'espressione sarà separato da uno spazio.    

Alcuni esempi:
1. `( * ( + 4 6 ) ( - 9 4 ) )` (risultato: `50`)
2. `( + ( * ( + 4 6 ) ( - 9 4 ) ) 10 )` (risultato: `60`)
3. `( / ( + ( x ( + 5.2 4.8 ) ( - 9.4 4.4 ) ) 10 ) 6 )` (risultato: `10.0`)

Il programma, oltre alle semplici funzioni `f(a,b)` dovrà avere i seguenti requisiti:
1. Espressioni innestate (vedi esempi `1` e `2`)
2. Operandi in virgola mobile (esempio `3`)
3. Due versioni: iterativa e ricorsiva

_**Protip**: gli esempi forniti posso essere utilizzati come test cases._
