# Context Retrieval Benchmark: AIWiki 0.4.0

## Performance Summary

| Data Set                  | Avrg. Performance   | Runtime |
|---------------------------|---------------------|---------|
| [spring](#spring)         | 0.4720000000000001  | 2181 ms |
| [bs4](#bs4)               | 0.528               | 1851 ms |
| [petfellows](#petfellows) | 0.9000000000000001  | 748 ms  |
| [critrole](#critrole)     | 0.32833333333333337 | 1599 ms |

## Question Summaries

### spring

| Question                                                                                     | Performance |
|----------------------------------------------------------------------------------------------|-------------|
| How do I define a listener class or method that is executed during entity lifecycle events?  | 0.93        |
| How can I extend an auto-generated Spring JPA repository with custom methods or behavior?    | 0.67        |
| What configurations do I need to perform to use Springs JPA integration?                     | 0.32        |
| Can I reference native SQL queries with Spring JPA?                                          | 0.44        |
| Which page can I read up on to learn about mapping query result tuples to custom POJO types? | 0.00        |

### bs4

| Question                                                                         | Performance |
|----------------------------------------------------------------------------------|-------------|
| How do you select the children of a div container with bs4?                      | 0.48        |
| I want to search elements using the jQuery CSS syntax. How can I do that in bs4? | 0.50        |
| What methods for searching elements in the markup tree are there?                | 0.20        |
| Are there performance trade-offs one should be aware of when using bs4?          | 0.50        |
| What are Bs4's features? What can it do?                                         | 0.96        |

### petfellows

| Question                             | Performance |
|--------------------------------------|-------------|
| Who are the founders of Pet Fellows? | 0.85        |
| What features does the app offer?    | 0.95        |

### critrole

| Question                                                       | Performance |
|----------------------------------------------------------------|-------------|
| Does Imogen have a love interest?                              | 0.31        |
| Which of the members of Bells Hells was killed by Otohan Tull? | 0.31        |
| Who is Ira Wendegoth?                                          | 0.70        |
| In what episode did Ferne find her parents?                    | 0.00        |

## Data Set Details

### spring

1. **Question:** How do I define a listener class or method that is executed during entity lifecycle events?

- _Performance_: 0.9333333333333335
- _Correct segments_: 3 / 3

| Expected Segments                          | Actual Segments                            |
|--------------------------------------------|--------------------------------------------|
| Entity Callbacks [0]                       | Entity Callbacks [1]                       |
| Entity Callbacks [1]                       | Auditing [2]                               |
| Publishing Events from Aggregate Roots [0] | Entity Callbacks [0]                       |
|                                            | CDI Integration [0]                        |
|                                            | Publishing Events from Aggregate Roots [0] |

---

2. **Question:** How can I extend an auto-generated Spring JPA repository with custom methods or behavior?

- _Performance_: 0.6666666666666666
- _Correct segments_: 2 / 3

| Expected Segments                     | Actual Segments                       |
|---------------------------------------|---------------------------------------|
| Custom Repository Implementations [0] | Custom Repository Implementations [0] |
| Custom Repository Implementations [1] | Query Methods [0]                     |
| Custom Repository Implementations [2] | Custom Repository Implementations [2] |
|                                       | Creating Repository Instances [1]     |
|                                       | CDI Integration [0]                   |

---

3. **Question:** What configurations do I need to perform to use Springs JPA integration?

- _Performance_: 0.32
- _Correct segments_: 1 / 3

| Expected Segments | Actual Segments     |
|-------------------|---------------------|
| Configuration [0] | Auditing [2]        |
| Configuration [1] | Configuration [0]   |
| Configuration [2] | Spring Data JPA [0] |
|                   | CDI Integration [0] |
|                   | JPA [0]             |

---

4. **Question:** Can I reference native SQL queries with Spring JPA?

- _Performance_: 0.44000000000000006
- _Correct segments_: 1 / 2

| Expected Segments     | Actual Segments       |
|-----------------------|-----------------------|
| JPA Query Methods [4] | JPA Query Methods [8] |
| JPA Query Methods [2] | JPA Query Methods [0] |
|                       | JPA Query Methods [3] |
|                       | Query Methods [0]     |
|                       | JPA Query Methods [2] |

---

5. **Question:** Which page can I read up on to learn about mapping query result tuples to custom POJO types?

- _Performance_: 0.0
- _Correct segments_: 0 / 3

| Expected Segments | Actual Segments                   |
|-------------------|-----------------------------------|
| Projections [0]   | Defining Query Methods [4]        |
| Projections [1]   | Repository query return types [0] |
| Projections [2]   | Object Mapping [0]                |
|                   | JPA [0]                           |
|                   | Spring Data Extensions [3]        |

---

### bs4

1. **Question:** How do you select the children of a div container with bs4?

- _Performance_: 0.48000000000000004
- _Correct segments_: 1 / 2

| Expected Segments       | Actual Segments         |
|-------------------------|-------------------------|
| Navigating the tree [0] | Searching the tree [8]  |
| Navigating the tree [1] | Navigating the tree [0] |
|                         | Searching the tree [9]  |
|                         | Searching the tree [10] |
|                         | Searching the tree [4]  |

---

2. **Question:** I want to search elements using the jQuery CSS syntax. How can I do that in bs4?

- _Performance_: 0.5
- _Correct segments_: 1 / 2

| Expected Segments      | Actual Segments         |
|------------------------|-------------------------|
| Searching the tree [3] | Searching the tree [3]  |
| Searching the tree [4] | Searching the tree [8]  |
|                        | Searching the tree [0]  |
|                        | Quick Start [0]         |
|                        | Searching the tree [10] |

---

3. **Question:** What methods for searching elements in the markup tree are there?

- _Performance_: 0.2
- _Correct segments_: 1 / 5

| Expected Segments      | Actual Segments                   |
|------------------------|-----------------------------------|
| Searching the tree [0] | Searching the tree [0]            |
| Searching the tree [1] | Navigating the tree [0]           |
| Searching the tree [2] | Advanced parser customization [1] |
| Searching the tree [3] | Searching the tree [10]           |
| Searching the tree [4] | Quick Start [0]                   |

---

4. **Question:** Are there performance trade-offs one should be aware of when using bs4?

- _Performance_: 0.5
- _Correct segments_: 1 / 2

| Expected Segments   | Actual Segments                  |
|---------------------|----------------------------------|
| Troubleshooting [2] | Troubleshooting [2]              |
| Troubleshooting [3] | Specifying the parser to use [1] |
|                     | Beautiful Soup 3 [0]             |
|                     | Quick Start [0]                  |
|                     | Installing Beautiful Soup [0]    |

---

5. **Question:** What are Bs4's features? What can it do?

- _Performance_: 0.9600000000000001
- _Correct segments_: 1 / 1

| Expected Segments                | Actual Segments                  |
|----------------------------------|----------------------------------|
| Beautiful Soup Documentation [0] | Beautiful Soup 3 [0]             |
|                                  | Beautiful Soup Documentation [0] |
|                                  | Kinds of objects [1]             |
|                                  | Installing Beautiful Soup [0]    |
|                                  | Kinds of objects [2]             |

---

### petfellows

1. **Question:** Who are the founders of Pet Fellows?

- _Performance_: 0.8500000000000001
- _Correct segments_: 1 / 1

| Expected Segments | Actual Segments |
|-------------------|-----------------|
| team [0]          | startseite [0]  |
|                   | fellowships [0] |
|                   | download [0]    |
|                   | team [0]        |

---

2. **Question:** What features does the app offer?

- _Performance_: 0.9500000000000001
- _Correct segments_: 2 / 2

| Expected Segments | Actual Segments |
|-------------------|-----------------|
| startseite [0]    | download [0]    |
| fellowships [0]   | startseite [0]  |
|                   | fellowships [0] |
|                   | team [0]        |

---

### critrole

1. **Question:** Does Imogen have a love interest?

- _Performance_: 0.3066666666666667
- _Correct segments_: 1 / 3

| Expected Segments             | Actual Segments             |
|-------------------------------|-----------------------------|
| C3E65-A Path of Vengeance [2] | C3E12-Make It Fashion [3]   |
| C3E65-A Path of Vengeance [2] | C3E50-Red Moon Rising [4]   |
| C3E49-The Aurora Grows [7]    | C3E18-A Hungry Jungle [2]   |
|                               | C3E28-The Deathwish Run [4] |
|                               | C3E49-The Aurora Grows [7]  |

---

2. **Question:** Which of the members of Bells Hells was killed by Otohan Tull?

- _Performance_: 0.3066666666666667
- _Correct segments_: 1 / 3

| Expected Segments              | Actual Segments                  |
|--------------------------------|----------------------------------|
| C3E33-Blood and Dust [5]       | C3E34-What Dreams May Come [1]   |
| C3E33-Blood and Dust [6]       | C3E39-The Momentum of Murder [1] |
| C3E34-What Dreams May Come [1] | C3E39-The Momentum of Murder [2] |
|                                | C3E66-Aid of the Tempest [1]     |
|                                | C3E38-A Dark Balance [6]         |

---

3. **Question:** Who is Ira Wendegoth?

- _Performance_: 0.7000000000000001
- _Correct segments_: 3 / 4

| Expected Segments                         | Actual Segments                       |
|-------------------------------------------|---------------------------------------|
| C3E11-Chasing Nightmares [1]              | C3E44-Bawdy Basement Belligerence [6] |
| C3E11-Chasing Nightmares [2]              | C3E11-Chasing Nightmares [1]          |
| C3E11-Chasing Nightmares [3]              | C3E49-The Aurora Grows [5]            |
| C3E10-Ghosts, Dates, and Darker Fates [5] | C3E11-Chasing Nightmares [3]          |
|                                           | C3E11-Chasing Nightmares [2]          |

---

4. **Question:** In what episode did Ferne find her parents?

- _Performance_: 0.0
- _Correct segments_: 0 / 2

| Expected Segments              | Actual Segments                       |
|--------------------------------|---------------------------------------|
| C3E28-The Deathwish Run [5]    | C3E46-Night at the Ligament Manor [1] |
| C3E30-Reunion & Revelation [3] | C3E78-Campaign 3 Episode 78 [7]       |
|                                | C3E55-Hope Within History [4]         |
|                                | C3E52-Far From The Others [6]         |
|                                | C3E45-Ominous Lectures [5]            |

---
