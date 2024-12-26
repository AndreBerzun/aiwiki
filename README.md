![Header Image](https://github.com/user-attachments/assets/a2052f90-5002-4cc0-9e74-ba5b97060ebf)

# BIT BERT (Privater Prototyp)

#### **Beschreibung**

##### **Problem**  
In modernen IT-Landschaften stossen Entwickler auf eine grosse Menge an Know-how, das für ihre tägliche Arbeit entscheidend ist. Um effektiv arbeiten zu können, benötigen sie Wissen aus verschiedenen Bereichen:  

- **Schnittstellen zu anderen Systemen**: API-Spezifikationen, Datenformate, Fehlerbehebungen.  
- **Deployment- und Pipeline-Prozesse**: Deployment auf verschiedene Stages, Debugging von Pipeline-Issues.  
- **Monitoring-Systeme**: Alarmierungsprozesse, Log-Diagnosen, Performance-Analysen.  
- **Fachliche und organisatorische Prozesse**: Incident-Handling, Verantwortlichkeiten, Genehmigungsprozesse.  

Trotz umfangreicher Dokumentationen macht die Wissenssuche in Tools wie Confluence oft grosse Mühe, da genaue Begriffe, Dateinamen oder Verantwortliche bekannt sein müssen. Diese hohen Anfordeungen und gleichzeitig ungünstigen Tools führen zu:
- **Langen Einarbeitungszeiten**: Neue Mitarbeitende benötigen viel Zeit, um sich einen Überblick zu verschaffen.  
- **Ungenügender Wissenstranfer**: Das Wissen sammelt sich meist pro Team auf einzelne Senior-Kollegen, was ihre Auslastung erhöht und die Teamgeschwindigkeit bremst.  

Das Resultat: **Produktivitätsverlust**, **Frustration** und ineffiziente, „selbst gebastelte“ Lösungen, die von Normen des BITs und einzelner Trains abweichen.

---

##### **Lösung**  
Eine semantische Suchmaschine, die als zentrale Anlaufstelle für Recherchen dient und verschiedene Datenquellen einbindet.  

**Was bedeutet „semantisch“?**  
Das Informatikgebiet „Information Retrieval“ wurde durch KI-Entwicklungen der letzten zehn Jahre revolutioniert. Klassische Suchalgorithmen (Sparse Retrieval) matchen exakte Keywords, was oft unzureichend ist. Moderne Ansätze wie **Dense Retrieval** sortieren Suchergebnisse basierend auf ihrer **Bedeutung** statt auf reiner Textübereinstimmung und Keyword-Frequenz.  

**Wie funktioniert das?**  
- KI-Modelle wie BERT erzeugen mathematische Repräsentationen (Vektoren) für Benutzerabfragen und Dokumente.  
- Dense Retrieval Algorithmen vergleichen numerisch, wie „nah“ ein Dokument an der Abfrage liegt.  

Diese Technologie kann nicht nur Texte durchsuchen, sondern auch Inhalte wie **PDF-Dokumente**, **Tabellen**, **Bilder**, **Statistiken** und sogar **Quellcode** verstehen.  

**Erfolgreiche Beispiele aus der Praxis**  
Solche Lösungen – bekannt als **Retrieval Augmented Generation (RAG)** – werden bereits bei Unternehmen wie Google oder Stripe eingesetzt, um den internen Wissenstransfer zu optimieren.

---

##### **Beispiele**
1. **Abfrage**: Incidents im Zusammenhang mit den DMAK M2M-Schnittstellen
   - *Hintergrund*: es wurde ein neuer Incident im Zusammenhang mit der M2M-Schnittstelle des DMAK-Systems erstellt. Das Team erinnert sich wage, dass ein ähnliches Problem bereits vor kurzem bestand und recherchiert nun.
   - *Aktuelle Situation*: Zielsysteme wie Jira oder Remedy müssen einzeln durchsucht werden. Ergebnisse erscheinen nur, wenn der exakte Begriff „M2M-Schnittstellen“ verwendet wird.  
   - *BIT-BERT*: Liefert relevante Informationen unabhängig von der genauen Wortwahl, z. B. „Machine-to-Machine API-Probleme“ und zeigt Ergebnisse gleich für alle Quellsysteme an.  
![Result Example Jira+Incident](https://github.com/user-attachments/assets/7ec815f7-dd69-4e68-ba2b-20e1cb64c8c4)

2. **Abfrage**: Openshift Pipeline step "acs-scan" schlägt fehlt "ERROR: scanning image failed due to violation of security policy"
   - *Hintergrund*: Ein Feature-PR ist durch einen fehlgeschlagenen Pipeline-Run blockiert. Der Entwickler kann das Pipeline-Problem alleine nicht lösen.
   - *Aktuelle Situation*: Der Entwickler könnte sich an einen Senior-Entwickler wenden, der mehr Erfahrung mit der Pipeline hat. Um das Problem eigenständig zu lösen/recherchieren, bedarf es eines gründlichen Verständnisses der Pipeline.
   - *BIT-BERT*: Erstellt basierend auf passender Confluence-Dokumentation eine AI-generierte Antwort.
![Result Example Confluence](https://github.com/user-attachments/assets/f3a85f76-9592-4ef9-9574-4c13686cf6a4)

3. **Abfrage**: Ist der Service CORE-IT E2 PartnerGET aktuell down?
   - *Hintergrund*: Erreichbarkeitsprobleme bei Umsystemen sorgen bei Non-Prod Umgebungen für Entwicklungsverzögerungen, da zunächst unklar ist, ob das eigene System verantwortlich ist. Bestehende Monitoring-Dashboards oder Wartungsblogs sind ausserhalb der Teams, die sie betreibenden, häufig unbekannt.
   - *Aktuelle Situation*: Man "ermogelt" sich den Erreichbarkeitsstatus bei Entwicklern aus den Nachbarteams, die man persönlich kennt.
   - *BIT-BERT*: s.u.
![Result Example Grafana](https://github.com/user-attachments/assets/68f208c4-81ec-46cf-b76f-22a00148c7e9)

4. **Abfrage**: Wie kann die Web Security Konfiguration von EDVS EF9 Backend angepasst werden?
   - *Hintergrund*: Beim Troubleshooting  kann es vorkommen, dass Entwickler in den Code von Umsystemen eintauchen müssen. Sich in diesem fremden Umfeld zurecht zu finden, ist nicht leicht. 
   - *BIT-BERT*: Ist in der Lage Code-Fragen zu verstehen und findet passende Ergebnisse.
![Result Example Bitbucket](https://github.com/user-attachments/assets/9156d846-fbbd-4025-b7b5-e84ef041ce74)


---

##### **Wie verbessert BIT-BERT unsere Arbeit?**
- **BIT-BERT ist der Domän-Export für die gesamte Technik des BITs**
- **Kontextverständnis**: Relevante Informationen werden auch bei ungenauen oder unvollständigen Suchabfragen gefunden.    
- **Effizienz**: Die Suchdauer wird durch semantisches Matching stark reduziert.  
- **Skalierbarkeit**: Funktioniert für unterschiedlichste Datenquellen – strukturiert und unstrukturiert.  

---

#### **Umsetzungsplan**  

**Modularer Aufbau**  
BIT-BERT wird modular und inkrementell entwickelt. Es besteht aus:  
1. **Search Engine**: Zentrale Komponente, die Indexierung und Ranking der Suchergebnisse steuert.  
2. **Document Provider**: Module, die Datenquellen wie Confluence, Jira oder Bitbucket anbinden.  

**Vorteil**  
Durch die Trennung der Kernfunktionalitäten kann die Suchmaschine mit übersehbarem Aufwand inkrementell entwickelt werden. Die Search Engine selbst kann auf diese Weise sogar flexibel von einzelnen Entwicklungsteams wiederverwendet werden, um eigene Search Use Cases umzusetzen.

**Proof of Concept**  
Ein Prototyp der Search Engine wurde bereits in meiner Freizeit entwickelt. Dieser ist aktuell in der Lage, aus Markdown-Files bestehende Wikis zu indezieren. Der Code ist auf GitHub verfügbar: [[Link zum Repo]](https://github.com/AndreBerzun/aiwiki)

**Beispiel für eine schrittweise Anbindung von Datenquellen**:  
1. **Confluence + Jira**  
2. **Remedy**
3. **Bitbucket**: Pull-Requests finden, Code analysieren, schneller Überblick zu Applikationen anderer (Nachbar-)Teams
4. **Grafana**: Monitoring-Systeme durchsuchen.
5. **LDAP**: Organisationsstrukturen und Teamzugehörigkeiten.
6. **Bundesgesetze**: Unterstützung bei fachlichen Rückfragen.
