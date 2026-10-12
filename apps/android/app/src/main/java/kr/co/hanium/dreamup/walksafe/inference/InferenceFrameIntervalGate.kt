package kr.co.hanium.dreamup.walksafe.inference

/** Runs the first frame and then one frame out of every configured interval. */
internal class InferenceFrameIntervalGate(
    private val intervalFrames: Int,
) {
    private var framesUntilRun = 0

    init {
        require(intervalFrames > 0) { "intervalFrames must be positive" }
    }

    fun shouldRun(): Boolean {
        if (framesUntilRun == 0) {
            framesUntilRun = intervalFrames - 1
            return true
        }
        framesUntilRun -= 1
        return false
    }
}
