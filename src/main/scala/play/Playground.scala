package play

import cats.data.State

import scala.language.higherKinds

object Playground {
  sealed trait TimerStatus
  case class Started(startTime: Long)                  extends TimerStatus
  case class Completed(startTime: Long, endTime: Long) extends TimerStatus

  // Smart constructors
  def started(startTime: Long): TimerStatus          = Started(startTime)
  def completed(start: Long, end: Long): TimerStatus = Completed(start, end)

  trait Clock[P[_]] {

    /**
      *
      * @return The current elapsed time from the Epoch in milliseconds
      */
    def now: P[Long]
  }

  case class ReportMeasureEffect(reportId: String, timerStatus: Completed)

  case class TestState(clockLastValue: Long = 0, reportEffect: Option[ReportMeasureEffect] = None)

  type TestContext[A] = State[TestState, A]

  class ClockForTest(increment: Long) extends Clock[TestContext] {
    override def now: TestContext[Long] = State {
      testState => {
        val newClockValue = testState.clockLastValue + increment
        (testState.copy(clockLastValue = newClockValue), newClockValue) // (TestState, Long)
      }
    }
  }
}
