# Context Retrieval Benchmark: Test

## Performance Summary

| Data Set | Avrg. Performance | Runtime |
|-|-|-|
| [Technical Documentation](#Technical Documentation) | 0.5 | 350 ms |
| [Fictional story](#Fictional story) | 0.9 | 150 ms |

## Question Summaries

### Technical Documentation

| Question | Performance |
|-|-|
| Example Question? | 0.75 |
| Another question? | 0.90 |

### Fictional story

| Question | Performance |
|-|-|
| Example Question? | 0.68 |

## Data Set Details

### Technical Documentation

1. **Question:** Example Question?
- _Performance_: 0.75
- _Correct segments_: 2 / 2

| Expected Segments | Actual Segments |
|-|-|
| Page 1 [0] | Page 1 [0] |
| Page 2 [1] | Page 2 [1] |

---

2. **Question:** Another question?
- _Performance_: 0.9
- _Correct segments_: 1 / 2

| Expected Segments | Actual Segments |
|-|-|
| Page 1 [0] | Page 1 [0] |
| Page 2 [4] | Page 5 [8] |

---

### Fictional story

1. **Question:** Example Question?
- _Performance_: 0.68
- _Correct segments_: 2 / 2

| Expected Segments | Actual Segments |
|-|-|
| Page 2 [1] | Page 1 [0] |
| Page 1 [0] | Page 2 [1] |

---