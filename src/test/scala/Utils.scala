import org.scalatest.FunSuite
import utils.Utils

class Utils extends FunSuite {

  test("Utils.roundUpNear05(): values 0.01 to 0.05 = 0.05 "){
    for ( n <-  BigDecimal("0.01") to BigDecimal("0.05") by BigDecimal("0.01")) {

      assert(Utils.roundUpNear05(n.doubleValue()) == 0.05)

    }
  }

  test("Utils.roundUpNear05(): values 0.01 to 0.05 = 0.05 (2)"){

    assert(Utils.roundUpNear05(.01) == .05)
    assert(Utils.roundUpNear05(.02) == .05)
    assert(Utils.roundUpNear05(.03) == .05)
    assert(Utils.roundUpNear05(.04) == .05)
    assert(Utils.roundUpNear05(.05) == .05)

  }

  test("Utils.roundUpNear05(): values 0.06 to 0.09 = 0.1 "){
    for ( n <-  BigDecimal("0.06") to BigDecimal("0.09") by BigDecimal("0.01")) {

      assert(Utils.roundUpNear05(n.doubleValue()) == 0.1)

    }
  }

  test("Utils.roundUpNear05(): values 0.06 to 0.09 = 0.1 (2)"){

    assert(Utils.roundUpNear05(.06) == .1)
    assert(Utils.roundUpNear05(.07) == .1)
    assert(Utils.roundUpNear05(.08) == .1)
    assert(Utils.roundUpNear05(.09) == .1)

  }

}


