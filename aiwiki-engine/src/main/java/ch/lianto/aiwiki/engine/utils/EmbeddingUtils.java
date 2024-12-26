package ch.lianto.aiwiki.engine.utils;

public class EmbeddingUtils {

    public static double cosineSimilarity(double[] vectorX, double[] vectorY) {
        if (vectorX.length != vectorY.length) {
            throw new IllegalArgumentException("Vectors lengths must be equal");
        }

        double dotProduct = dotProduct(vectorX, vectorY);
        double normX = norm(vectorX);
        double normY = norm(vectorY);

        if (normX == 0 || normY == 0) {
            throw new IllegalArgumentException("Vectors cannot have zero norm");
        }

        return dotProduct / (Math.sqrt(normX) * Math.sqrt(normY));
    }

    public static double dotProduct(double[] vectorX, double[] vectorY) {
        if (vectorX.length != vectorY.length) {
            throw new IllegalArgumentException("Vectors lengths must be equal");
        }

        double result = 0;
        for (int i = 0; i < vectorX.length; ++i) {
            result += vectorX[i] * vectorY[i];
        }

        return result;
    }

    public static double norm(double[] vector) {
        return dotProduct(vector, vector);
    }

    public static void main(String[] args) {
        for (double[] embedding : EmbeddingConstants.GROUND_TRUTHS)
            System.out.println("Ground Truth Cosine Sim: " + cosineSimilarity(embedding, EmbeddingConstants.QUERY));

        for (double[] embedding : EmbeddingConstants.SEARCH_RESULTS)
            System.out.println("Search Result Cosine Sim: " + cosineSimilarity(embedding, EmbeddingConstants.QUERY));
    }
}
