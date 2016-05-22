package com.mezentsev_tomin.adminpanel.util;


import com.mezentsev_tomin.adminpanel.beans.User;
import org.hibernate.Session;
import org.testng.annotations.Test;


import javax.swing.*;
import java.sql.Connection;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;


/**
 * Created by Mezentsev.Y on 5/19/2016.
 */
public class HibernateUtilTest {
    @Test
    public void getSessionFactory() {
        Session session = null;
        try {
            session =  HibernateUtil.getSessionFactory().openSession();
            assertNotNull(session);
            assertTrue(session.isOpen());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }


    }



}