# Context Retrieval Benchmark: Test

## Performance Summary

| Data Set | Avrg. Score | Runtime |
|-|-|-|
| [Technical Documentation](#Technical Documentation) | 0.5 | 350 ms |
| [Fictional story](#Fictional story) | 0.9 | 150 ms |

## Question Summaries

### Technical Documentation

| Question | Score | Answer |
|-|-|-|
| Example Question? | 0.75 | ✅/❌ |
| Another question? | 0.90 | ✅/❌ |

### Fictional story

| Question | Score | Answer |
|-|-|-|
| Example Question? | 0.68 | ✅/❌ |

## Data Set Details

### Technical Documentation

1. **Question:** Example Question?

- _Score_: 0.75
- _Precision_: 0.65
- _Recall_: 0.8

| Expected Chunks |  |
|-|-|
| Page 1 | Test Quote |
| Page 2 | Hanging on in quite desperation is the English way |

| Found Chunks | Relevant |  |
|-|-|-|
| Page 1 | ✅ | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam. |
| Page 2 | ✅ | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam. |

---

2. **Question:** Another question?

- _Score_: 0.9
- _Precision_: 0.9
- _Recall_: 0.9

| Expected Chunks |  |
|-|-|
| Page 1 | You get a shiver in the dark, It's a raining in the park but meantime |
| Page 2 | South of the river you stop and you hold everything, A band is blowing Dixie, double four time |

| Found Chunks | Relevant |  |
|-|-|-|
| Page 4 | ✅ | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam. |

---

### Fictional story

1. **Question:** Example Question?

- _Score_: 0.68
- _Precision_: 0.68
- _Recall_: 0.68

| Expected Chunks |  |
|-|-|
| Page 1 | And Harry doesn't mind, if he doesn't, make the scene, He's got a daytime job, he's doing alright |
| Page 2 | South of the river you stop and you hold everything, A band is blowing Dixie, double four time |

| Found Chunks | Relevant |  |
|-|-|-|
| Page 2 | ✅ | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam. |
| Page 1 | ❌ | Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas finibus lectus lorem, nec aliquam. |

---