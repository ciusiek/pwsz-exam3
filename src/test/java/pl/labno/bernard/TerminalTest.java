package pl.labno.bernard;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
public class TerminalTest {
    @Rule
public ExpectedException expectedException= ExpectedException.none();
    @Test
    public void pierwsza_Null_theowIllegalArgumentException() {
        Connection connection= Mockito.mock(Connection.class);
        Terminal t=new Terminal(connection);
        expectedException.expect(IllegalArgumentException.class);
        t.sendLine(null);
    }
    @Test
    public void druga_noCon_throwIllegalArgumentException() {
        Connection con= Mockito.mock(Connection.class);
        when(con.isConnected()).thenReturn(false);
        Terminal ter=new Terminal(con);
        expectedException.expect(IllegalStateException.class);
        ter.sendLine("Test");
    }
    @Test
          public void trzecia_connectionSendLineThrowException_throwllegalArgumentException() {
        Connection con= Mockito.mock(Connection.class);
        when(con.isConnected()).thenReturn(true);
        when(con.sendLine("Test")).thenThrow(UnknownCommandException.class);
        Terminal ter=new Terminal(con);
        expectedException.expect(IllegalStateException.class); ter.sendLine("Test");
    }
    @Test
          public void czwarta_noConnection_ErrorMassage() {
        Connection con= Mockito.mock(Connection.class);
        when(con.isConnected()).thenReturn(false);
        Terminal ter=new Terminal(con);
        try { ter.sendLine("Test");
        }
        catch(IllegalStateException e){}
        Assert.assertEquals("Terminal is not connected", ter.getErrorMessage());
    }
}