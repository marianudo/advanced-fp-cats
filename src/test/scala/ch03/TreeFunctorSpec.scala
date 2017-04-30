package ch03

import org.scalatest.FlatSpec

import ch03.Tree._

class TreeFunctorSpec extends FlatSpec {
  "A Leaf" should "map properly" in {
    val leaf1 = leaf(10)

    assert(leaf1.map(_ * 3) == Leaf(30))
  }

  "A Branch with two leaves, one on each side" should "map properly" in {
    val tree: Tree[Int] = branch(
      leaf(10),
      leaf(20)
    )

    assert(tree.map(_ * 3) == Branch(Leaf(30), Leaf(60)))
  }

  "A branch with three leaves, two on the left and one on the right" should "map properly" in {
    val tree: Tree[Int] = Branch(
      branch(
        leaf(5), leaf(10)
      ), leaf(15)
    )

    assert(tree.map(_ * 2) == Branch(Branch(Leaf(10), Leaf(20)), Leaf(30)))
  }
}
