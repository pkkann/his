Høne indskrivnings system (HISM)
================================
- Opret person - OK
- Opret bruger - OK
- Rediger person
- Slet person
- Søg på navn - OK
- Søg på fødselsdags dato - OK
- Søg på alle - OK
- Person skal udløbe - OK
- Sæt person i karantæne i en periode
- Sæt person i karantæne for altid
- Send besked til admin
- Alt skal gemmes i database - OK
- Alt skal gemmes i objekter - OK
- Indskriv person
- Rediger indskrivning af person
- 


Note 12-09-2013 kl 17:40
========================
Nå man forsøger at slette en bruger som har et objekt efter sig i arraylisten, laver den index
out of bound exception med kommentaren 2 > 1.
Dette burde kunne løses ved ikke at indsætte objekterne på index i forhold til deres ID ved
hentning af objekter i databasen.

Men det virker dog som om at arraylisten ikke kan finde ud af at rykke objekterne så hullet udfyldes...
