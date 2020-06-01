package sampleQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class QueueTest {

	/**
	 * Tests for Queue.
	 */

	private static final String SOME_ITEM = "some-content";
	private Queue<String> q;


	@Test
	@DisplayName("is instantiated with new Queue()")
	void isInstantiatedWithNew() {
		new Queue<>();
	}

	@BeforeEach
	void init() {
		this.q = new Queue<String>(2);
	}

	@Test
	@DisplayName("Verify Queue isEmpty when queue is initialized")
	void isEmptyShouldGiveTrueOnQueueInit() {
		assertTrue(q.isEmpty());
	}

	//Example of a test that passes when some function returns false.
	@Test
	@DisplayName("Verify Queue isEmpty returns false when queue is not empty")
	void isEmptyShouldGiveFalseWhenQueueIsNotEmpty() {
		this.q.enqueue(SOME_ITEM);
		this.q.enqueue(SOME_ITEM);
		assertFalse(q.isEmpty());
	}

	@Test
	@DisplayName("Verify Enqueue Throws an exception on full")
	void enqueueShouldThrowExecptionOnFull(){
		q.enqueue(SOME_ITEM);
		q.enqueue(SOME_ITEM);
		RuntimeException e = assertThrows(RuntimeException.class, () -> q.enqueue(SOME_ITEM));
		assertEquals("Maximum Size of queue reached, cannot add more.", e.getMessage());
	}

	@Test
	@DisplayName("Queue should be empty after all elements have been removed")
	void isEmptyShoutlReturnTrueAfterAllElementsAreRemoved(){
		q.enqueue(SOME_ITEM);
		q.enqueue(SOME_ITEM);
		q.removeAll();
		assertTrue(q.isEmpty());
	}

	@Nested
	@DisplayName("when new")
	class WhenNew {

		@BeforeEach
		void createNewQueue() {
			q = new Queue<>();
		}

		@Test
		@DisplayName("is empty")
		void isEmpty() {
			assertTrue(q.isEmpty());
		}

		@Test
		@DisplayName("throws NoSuchElementException when dequeued")
		void throwsExceptionWhenDequeued() {
			assertThrows(NoSuchElementException.class, () -> q.dequeue());
		}

		@Test
		@DisplayName("throws NoSuchElementException when peeked")
		void throwsExceptionWhenPeeked() {
			assertThrows(NoSuchElementException.class, () -> q.peek());
		}

		@Nested
		@DisplayName("after enqueueing an element")
		class AfterPushing {

			String anElement = "an element";

			@BeforeEach
			void enqueueAnElement() {
				q.enqueue(anElement);
			}

			@Test
			@DisplayName("it is no longer empty")
			void isNotEmpty() {
				assertFalse(q.isEmpty());
			}

			@Test
			@DisplayName("returns the element when dequeued and is empty")
			void returnElementWhenPopped() {
				assertEquals(anElement, q.dequeue());
				assertTrue(q.isEmpty());
			}

			@Test
			@DisplayName("returns the element when peeked but remains not empty")
			void returnElementWhenPeeked() {
				assertEquals(anElement, q.peek());
				assertFalse(q.isEmpty());
			}
		}
	}
	
}