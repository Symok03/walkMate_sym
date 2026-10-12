package kr.co.hanium.dreamup.walksafe.inference

import kr.co.hanium.dreamup.walksafe.depth.DetectionCandidate
import kr.co.hanium.dreamup.walksafe.depth.bboxIoU

/** Combines the primary WalkMate output with a COCO class-0 person specialist. */
internal object PersonDetectionFusion {
    fun merge(
        primary: List<DetectionCandidate>,
        auxiliary: List<DetectionCandidate>,
        iouThreshold: Float = DEFAULT_IOU_THRESHOLD,
    ): List<DetectionCandidate> {
        require(iouThreshold in 0f..1f)
        val nonPeople = primary.filterNot(::isPerson)
        val people = (primary.asSequence() + auxiliary.asSequence())
            .filter(::isPerson)
            .sortedByDescending(DetectionCandidate::detectionConfidence)
            .fold(mutableListOf<DetectionCandidate>()) { kept, candidate ->
                if (kept.none { bboxIoU(it.bboxNorm, candidate.bboxNorm) >= iouThreshold }) {
                    kept += candidate
                }
                kept
            }
        return nonPeople + people
    }

    private fun isPerson(candidate: DetectionCandidate): Boolean =
        candidate.className.equals(PERSON_CLASS, ignoreCase = true)

    private const val PERSON_CLASS = "person"
    private const val DEFAULT_IOU_THRESHOLD = 0.55f
}
