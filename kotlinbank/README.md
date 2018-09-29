# Esercizio 1 – KotlinBank

L'obiettivo è di sviluppare un semplice programma Kotlin per la gestione di un conto corrente bancario.  
Un conto corrente ha un proprietario ed una quantità di denaro iniziale. Le operazioni consentite 
sul conto sono il prelievo e il versamento di denaro. La valuta supportata è l'euro.  
Al primo accesso all'utente verranno richiesti i dati per poter aprire il proprio conto corrente.  
Le volte successive verrà proposto all'utente il saldo corrente (importo+valuta) ed un menu dal quale selezionare 
l'operazione che vuole intraprendere sul conto. Il menu avrà una voce `q` che consente l'uscita dal sistema.    


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
