import java.lang.Object;
import com.domainlanguage.time.TimeInterval;
import com.domainlanguage.money.Money;
import com.domainlanguage.time.TimePoint;
import com.domainlanguage.time.Duration;

import junit.framework.TestCase;
public class TimeAndMoney_Documented_Test_Pretty extends TestCase { 
  public void test0() {
    //Test passes if i0 is set to: 0
    Integer i0 = new Integer(100);
    //Test passes if duration0 is set to: 
    Duration duration0 = Duration.days((int)i0);
    Integer i1 = new Integer(100);
    Duration duration1 = Duration.years((int)i1);
    Integer i2 = new Integer(0);
    Integer i3 = new Integer(100);
    Integer i4 = new Integer(1);
    Integer i5 = new Integer(1);
    Integer i6 = new Integer(100);
    Integer i7 = new Integer((-1));
    Integer i8 = new Integer((-1));
    TimePoint timePoint0 = TimePoint.atGMT((int)i2, (int)i3, (int)i4, (int)i5, (int)i6, (int)i7, (int)i8);
    TimeInterval timeInterval0 = duration1.startingFrom(timePoint0);
    Integer i9 = new Integer(0);
    Integer i10 = new Integer(100);
    Integer i11 = new Integer(1);
    Integer i12 = new Integer(1);
    Integer i13 = new Integer(100);
    Integer i14 = new Integer((-1));
    Integer i15 = new Integer((-1));
    TimePoint timePoint1 = TimePoint.atGMT((int)i9, (int)i10, (int)i11, (int)i12, (int)i13, (int)i14, (int)i15);
    Integer i16 = new Integer(100);
    Duration duration2 = Duration.years((int)i16);
    Integer i17 = new Integer(0);
    Integer i18 = new Integer(100);
    Integer i19 = new Integer(1);
    Integer i20 = new Integer(1);
    Integer i21 = new Integer(100);
    Integer i22 = new Integer((-1));
    Integer i23 = new Integer((-1));
    TimePoint timePoint2 = TimePoint.atGMT((int)i17, (int)i18, (int)i19, (int)i20, (int)i21, (int)i22, (int)i23);
    TimeInterval timeInterval1 = duration2.startingFrom(timePoint2);
    Integer i24 = new Integer(100);
    Duration duration3 = Duration.years((int)i24);
    Integer i25 = new Integer(0);
    Integer i26 = new Integer(100);
    Integer i27 = new Integer(1);
    Integer i28 = new Integer(1);
    Integer i29 = new Integer(100);
    Integer i30 = new Integer((-1));
    Integer i31 = new Integer((-1));
    TimePoint timePoint3 = TimePoint.atGMT((int)i25, (int)i26, (int)i27, (int)i28, (int)i29, (int)i30, (int)i31);
    TimeInterval timeInterval2 = duration3.startingFrom(timePoint3);
    TimePoint timePoint4 = timeInterval2.start();
    boolean b0 = timePoint2.isAfter(timeInterval2);
    Integer i32 = new Integer(0);
    Integer i33 = new Integer(100);
    Integer i34 = new Integer(1);
    Integer i35 = new Integer(1);
    Integer i36 = new Integer(100);
    Integer i37 = new Integer((-1));
    Integer i38 = new Integer((-1));
    TimePoint timePoint5 = TimePoint.atGMT((int)i32, (int)i33, (int)i34, (int)i35, (int)i36, (int)i37, (int)i38);
    Double d0 = new Double(0.0d);
    Money money0 = Money.euros((double)d0);
    Short s0 = new Short((short)(-1));
    boolean b1 = money0.equals((Object)s0);
    TimeInterval timeInterval3 = new TimeInterval(timePoint1, b0, timePoint5, b1);
    TimeInterval timeInterval4 = TimeInterval.over(timePoint0, timePoint5);
    Byte byte0 = new Byte((byte)100);
    boolean b2 = timePoint5.equals((Object)byte0);
    //Test passes if i39 is: (int)>0
    Integer i39 = new Integer(0);
    Integer i40 = new Integer(100);
    Integer i41 = new Integer(1);
    Integer i42 = new Integer(1);
    Integer i43 = new Integer(100);
    Integer i44 = new Integer((-1));
    Integer i45 = new Integer((-1));
    TimePoint timePoint6 = TimePoint.atGMT((int)i39, (int)i40, (int)i41, (int)i42, (int)i43, (int)i44, (int)i45);
    //Test passes if duration4 is set to: 10 days
    Duration duration4 = Duration.days((int)i39);
    TimePoint timePoint7 = timePoint5.plus(duration4);
    //Test passes if duration5 is set to: 
    Duration duration5 = duration0.minus(duration4);
    
    // Checks the contract:  equals-hashcode on duration0 and duration5
    assertTrue("Contract failed: equals-hashcode on duration0 and duration5", duration0.equals(duration5) ? duration0.hashCode() == duration5.hashCode() : true);
    
    // Checks the contract:  equals-hashcode on duration5 and duration0
    assertTrue("Contract failed: equals-hashcode on duration5 and duration0", duration5.equals(duration0) ? duration5.hashCode() == duration0.hashCode() : true);
  }

}