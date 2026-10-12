package kr.co.hanium.dreamup.walksafe.inference

import kr.co.hanium.dreamup.walksafe.depth.DetectionCandidate
import kr.co.hanium.dreamup.walksafe.depth.RectNorm
import org.junit.Assert.assertEquals
import org.junit.Test

class PersonDetectionFusionTest {
    @Test
    fun keepsPrimaryNonPeopleAndHighestConfidencePersonPerOverlapGroup() {
        val primary = listOf(
            detection("traffic_cone", 0.8f, 0.05f, 0.05f, 0.1f, 0.2f),
            detection("person", 0.6f, 0.2f, 0.1f, 0.3f, 0.7f),
        )
        val auxiliary = listOf(
            detection("person", 0.9f, 0.21f, 0.11f, 0.3f, 0.7f),
            detection("person", 0.7f, 0.65f, 0.15f, 0.2f, 0.6f),
            detection("bicycle", 0.99f, 0f, 0f, 1f, 1f),
        )

        val merged = PersonDetectionFusion.merge(primary, auxiliary)

        assertEquals(listOf("traffic_cone", "person", "person"), merged.map { it.className })
        assertEquals(listOf(0.8f, 0.9f, 0.7f), merged.map { it.detectionConfidence })
    }

    @Test
    fun retainsPrimaryPersonWhenItHasHigherConfidence() {
        val primary = listOf(detection("person", 0.95f, 0.2f, 0.1f, 0.3f, 0.7f))
        val auxiliary = listOf(detection("person", 0.8f, 0.21f, 0.11f, 0.3f, 0.7f))

        val merged = PersonDetectionFusion.merge(primary, auxiliary)

        assertEquals(1, merged.size)
        assertEquals(0.95f, merged.single().detectionConfidence)
    }

    private fun detection(
        className: String,
        confidence: Float,
        x: Float,
        y: Float,
        width: Float,
        height: Float,
    ) = DetectionCandidate(className, confidence, RectNorm(x, y, width, height))
}
