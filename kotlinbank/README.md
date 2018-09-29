# KotlinBank

### Obiettivo

Eseguire il porting in Kotlin di un semplice programma Java per la gestione 
di un conto corrente bancario.

### Startup

- Clonare questa repository in locale
- Aprire con IntelliJ il progetto `kotlinbank` 
- Creare sotto `src/main` la cartella `kotlin`: IntelliJ la riconoscerà come
cartella di sorgenti
- Creare il tuo package nella cartella appena creata
- Let's go!   

**Non utilizzare la conversione da Java a Kotlin di IntelliJ.**    
**Spero sia nel tuo interesse sviluppare il programma da zero! :)**    

Esegui il programma clonato per avere un'idea effettiva sul funzionamento che la
versione Kotlin deve avere. I requisiti sono dettagliati in questo README.

_PS: Non effettuare il porting dei bug che troverai :)_

### Requisiti 
  
Un conto corrente ha un proprietario ed una quantità di denaro iniziale.    
Le operazioni consentite sul conto sono il prelievo e il versamento di denaro.  
La valuta supportata è l'**euro**.  

Al primo accesso all'utente verranno richiesti i dati per poter aprire il proprio conto corrente.  
Le volte successive verrà proposto all'utente il saldo corrente (importo+valuta) ed un menu dal quale selezionare 
l'operazione che vuole intraprendere sul conto. Il menu avrà una voce `q` che consente l'uscita dal sistema.    

Nel caso l'utente finisca il denaro sul conto, il programma dovrà terminare.

Le feature verranno implementate in modo incrementale.  
1. Lettura dati iniziali del conto da standard input (nome utente e importo iniziale)
2. Visualizzazione importo iniziale
3. Visualizzazione menu
4. Lettura scelta utente con opportuna validazione
5. Funzionalità di deposito  
    a. Lettura e validazione importo da depositare  
    b. Feedback sull'operazione
6. Funzionalità di prelievo  
    a. Lettura e validazione importo da prelevare  
    b. Feedback sull'operazione
