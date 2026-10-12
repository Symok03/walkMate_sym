package kr.co.hanium.dreamup.walksafe.inference

import org.junit.Assert.assertEquals
import org.junit.Test

class InferenceFrameIntervalGateTest {
    @Test
    fun intervalTwoRunsFirstAndEveryOtherFrame() {
        val gate = InferenceFrameIntervalGate(2)

        assertEquals(listOf(true, false, true, false, true), List(5) { gate.shouldRun() })
    }

    @Test
    fun intervalOneRunsEveryFrame() {
        val gate = InferenceFrameIntervalGate(1)

        assertEquals(listOf(true, true, true), List(3) { gate.shouldRun() })
    }
}
