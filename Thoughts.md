# Thoughts

### Game Plan
[x] Delve into danswers implementation
[x] Pick a eval framework 
[X] How does ir_measures work in relation to chunks? Figure out how to wrap the tool
[X] Dive more into BEIR, understand data sets and maybe use them
[ ] Generate more synthetic test data for my datasets
  - Create ground truth with human-labeled graded qrels
  - Let LLM grade the same ground truth and compare results
  - Get more queries and possible matching documents by pooling using different search methods
  - Throw new (query, documents) pairs together and let LLM rate grades
[ ] ColPali?
[ ] Implement BM25 baseline
[ ] Implement Vespa? Start improving search
[ ] Understand how to fine-tune models for domain-specific data

### Current steps
[ ] When sampling documents, how do I pick the top k documents? 
    Can we normalize the scores that are returned from the sampling systems?
[ ] In the EvalService when converting my custom qrels and search for matching page chunks, split up the quote and search for chunks by individual sentences. Merge distinct chunks afterwards
    
   
### Zero Shot Ranking - Jo Bergum
- Search Performance Ranking (from baseline to best):
  - BM25
  - Hybrid
  - In-domain neural embedding trained on synthetic data
  - In-domain neural embedding trained on synthetic data + cross-encoder
