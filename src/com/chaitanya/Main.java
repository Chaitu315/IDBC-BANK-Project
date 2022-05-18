package com.chaitanya;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main
{
    public static void main(String[] args) throws SQLException
    {
        System.out.println("-------------------WELCOME TO IDBC BANK PORTAL-----------------\n");
        while(true)
        {
            System.out.println("Which Service Do You Want\n1.To open New Account\n2.Withdraw Amount\n3.Deposit Amount\n4.Check Balance" +
                    "\n5.Fund Transfer\n6.Calculate Interest\n7.Exit\n");

            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            int id;

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/IDBC_BANK", "root", "password@123");

            switch(choice)
            {

                case 1:
                    System.out.println("Which Type Of Account You Want\n1.Save Account\n2.Pay Account\n");
                    Scanner sc = new Scanner(System.in);
                    int option = sc.nextInt();

                    if(option==1)
                    {
                        System.out.println("-----------Customer Registration----------\n");
                        System.out.println("Enter Your Full Name");
                        Scanner sc1 = new Scanner(System.in);
                        String Name = sc1.nextLine();
                        System.out.println("Enter Your Date Of Birth in Format - (yyyy-mm-dd)");
                        Scanner sc2 = new Scanner(System.in);
                        String date_of_birth = sc2.nextLine();
                        System.out.println("Enter Your Age");
                        Scanner sc3 = new Scanner(System.in);
                        int cust_age = sc3.nextInt();
                        System.out.println("Enter Your Gender Male/Female");
                        Scanner sc4 = new Scanner(System.in);
                        String gender = sc4.nextLine();

                        boolean result = false;
                        String mobile = null;
                        while(result==false)
                        {
                            System.out.println("Enter Your 10 digit Mobile Number");
                            Scanner sc5 = new Scanner(System.in);
                            mobile = sc5.nextLine();

                            Pattern obj = Pattern.compile("^\\d{10}$");
                            Matcher matchObj = obj.matcher(mobile);
                            if(matchObj.find()!=true)
                            {
                                System.out.println("Please Re-Enter valid mobile Number\n");
                                result = false;
                            }
                            else
                            {
                                result = true;
                            }
                        }

                        boolean result1 = false;
                        String email = null;
                        while(result1==false)
                        {
                            System.out.println("Enter Your Email Id in Format - (abcde@gmail.com) ");
                            Scanner sc8 = new Scanner(System.in);
                            email = sc8.nextLine();

                            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                            boolean result2 = email.matches(regex);
                            if(result2==true)
                            {
                                result1 = true;
                            }
                            else
                            {
                                System.out.println("Please Re-Enter valid Email Id\n");
                                result1 = false;
                            }
                        }

                        System.out.println("Enter Your Address");
                        Scanner sc9 = new Scanner(System.in);
                        String adress = sc9.nextLine();

                        PreparedStatement st = con.prepareStatement("insert into customer(CUST_ID,CUST_NAME,DOB,AGE,GENDER,MOBILE_N0,EMAIL_ID,ADRESS) values(?,?,?,?,?,?,?,?)");

                        id = 100 + (int) (Math.random()*899);

                        st.setInt(1,id);
                        st.setString(2,Name);
                        st.setDate(3, Date.valueOf(date_of_birth));
                        st.setInt(4,cust_age);
                        st.setString(5,gender);
                        st.setString(6,mobile);
                        st.setString(7,email);
                        st.setString(8, adress);

                        if(cust_age<18)
                        {
                            System.out.println("You Are Not Eligible For Creating New Bank Account !!!");
                        }
                        else
                        {
                            int row1 = st.executeUpdate();
                            System.out.println("Your Details Registered Successfully...\n");
                            if(row1>0)
                            {

                                PreparedStatement st1 = con.prepareStatement("insert into ACCOUNT_DETAILS(ACCOUNT_NO,ACCOUNT_TYPE,BALANCE,AC_CREATED_DATE,CUST_ID) values(?,?,?,?,?)");

                                long accountNumber;
                                Random rand = new Random();
                                accountNumber = (rand.nextInt(1000000)+ 1000000000L) * (rand.nextInt(900)+100);

                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime now = LocalDateTime.now();

                                long millis = System.currentTimeMillis();
                                java.sql.Date date = new java.sql.Date(millis);


                                System.out.println("Minimum Amount 1000 Required To Open Account... ");
                                System.out.println("Enter Amount To Deposit");
                                Scanner sc11 = new Scanner(System.in);
                                double amount = sc11.nextDouble();

                                st1.setLong(1,accountNumber);
                                st1.setString(2,"Save Account");
                                st1.setDouble(3,amount);
                                st1.setDate(4,date);
                                st1.setInt(5,id);
                                int row2 = st1.executeUpdate();

                                if(row2>0)
                                {
                                    System.out.println("New Account Created Successfully...");
                                    System.out.println("Your Account Number : "+accountNumber);
                                    System.out.println("Your Customer Id    : "+id+"\n");
                                }

                            }
                        }
                    }
                    else if(option==2)
                    {
                        System.out.println("-----------Customer Registration----------\n");
                        System.out.println("Enter Your Full Name");
                        Scanner sc1 = new Scanner(System.in);
                        String Name = sc1.nextLine();
                        System.out.println("Enter Your Date Of Birth in Format - (yyyy-mm-dd)");
                        Scanner sc2 = new Scanner(System.in);
                        String date_of_birth = sc2.nextLine();
                        System.out.println("Enter Your Age");
                        Scanner sc3 = new Scanner(System.in);
                        int cust_age = sc3.nextInt();
                        System.out.println("Enter Your Gender Male/Female");
                        Scanner sc4 = new Scanner(System.in);
                        String gender = sc4.nextLine();

                        boolean result1 = false;
                        String mobile1 = null;
                        while(result1==false)
                        {
                            System.out.println("Enter Your 10 digit Mobile Number");
                            Scanner sc5 = new Scanner(System.in);
                            mobile1 = sc5.nextLine();

                            Pattern obj = Pattern.compile("^\\d{10}$");
                            Matcher matchObj = obj.matcher(mobile1);
                            if(matchObj.find()!=true)
                            {
                                System.out.println("Please Re-Enter valid mobile Number\n");
                                result1 = false;
                            }
                            else
                            {
                                result1 = true;
                            }
                        }

                        boolean result2 = false;
                        String email1 = null;
                        while(result2==false)
                        {
                            System.out.println("Enter Your Email Id in Format - (abcde@gmail.com) ");
                            Scanner sc8 = new Scanner(System.in);
                            email1 = sc8.nextLine();

                            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
                            boolean result3 = email1.matches(regex);
                            if(result3==true)
                            {
                                result2 = true;
                            }
                            else
                            {
                                System.out.println("Please Re-Enter valid Email Id\n");
                                result2 = false;
                            }
                        }

                        System.out.println("Enter Your Address");
                        Scanner sc9 = new Scanner(System.in);
                        String adress = sc9.nextLine();

                        PreparedStatement st = con.prepareStatement("insert into customer(CUST_ID,CUST_NAME,DOB,AGE,GENDER,MOBILE_N0,EMAIL_ID,ADRESS) values(?,?,?,?,?,?,?,?)");

                        id = 100 + (int) (Math.random()*899);
                        st.setInt(1,id);
                        st.setString(2,Name);
                        st.setDate(3, Date.valueOf((date_of_birth)));
                        st.setInt(4,cust_age);
                        st.setString(5,gender);
                        st.setString(6,mobile1);
                        st.setString(7,email1);
                        st.setString(8, adress);

                        if(cust_age<18)
                        {
                            System.out.println("You Age is Below 18 So You Are Not Eligible For Creating New Bank Account !!!\n");
                        }
                        else
                        {
                            int row1 = st.executeUpdate();
                            System.out.println("Your Details Registered Successfully...\n");
                            if(row1>0)
                            {
                                PreparedStatement st1 = con.prepareStatement("insert into ACCOUNT_DETAILS(ACCOUNT_NO,ACCOUNT_TYPE,BALANCE,AC_CREATED_DATE,CUST_ID) values(?,?,?,?,?)");

                                long accountNumber;
                                Random rand = new Random();
                                accountNumber = (rand.nextInt(1000000)+ 1000000000L) * (rand.nextInt(900)+100);

                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime now = LocalDateTime.now();

                                System.out.println("Minimum Amount 1000 Required To Open Account... ");
                                System.out.println("Enter Amount To Deposit");

                                long millis = System.currentTimeMillis();
                                java.sql.Date date1 = new java.sql.Date(millis);

                                System.out.println("Minimum Amount 2000 Required To Open Account... ");
                                System.out.println("Enter Amount To Deposit");
                                Scanner sc11 = new Scanner(System.in);
                                double amount = sc11.nextDouble();

                                st1.setLong(1,accountNumber);
                                st1.setString(2,"Pay Account");
                                st1.setDouble(3,amount);
                                st1.setDate(4,date1);
                                st1.setInt(5,id);
                                int row2 = st1.executeUpdate();

                                if(row2>0)
                                {
                                    System.out.println("New Account Created Successfully...");
                                    System.out.println("Your Account Number : "+accountNumber);
                                    System.out.println("Your Customer Id    : "+id+"\n");
                                }
                            }
                        }
                    }
                    else
                    {
                        System.out.println("INVALID CHOICE !!!\n");
                    }
                    break;
                case 2:
                    System.out.println("Enter Your Account Number");
                    Scanner Scan3 = new Scanner(System.in);
                    long Account1 = Scan3.nextLong();

                    PreparedStatement st5 = con.prepareStatement("select ACCOUNT_NO from ACCOUNT_DETAILS where ACCOUNT_NO = ?");
                    st5.setLong(1,Account1);
                    ResultSet row6 = st5.executeQuery();
                    int result1 = 0;
                    while (row6.next())
                    {
                        result1++;
                    }

                    if(result1==1)
                    {
                        System.out.println("\nEnter The Amount To be Withdraw");
                        Scanner Scan4 = new Scanner(System.in);
                        double Amount1 = Scan4.nextLong();

                        PreparedStatement st6 = con.prepareStatement("select ACCOUNT_NO,BALANCE from ACCOUNT_DETAILS where ACCOUNT_NO = ? AND BALANCE > ?");
                        st6.setLong(1,Account1);
                        st6.setDouble(2,Amount1);
                        ResultSet row7 = st6.executeQuery();
                        int result2 = 0;
                        while (row7.next())
                        {
                            result2++;
                        }
                        if(result2==1)
                        {
                            PreparedStatement st3 = con.prepareStatement("update ACCOUNT_DETAILS set BALANCE = BALANCE-? where ACCOUNT_NO = ?");
                            st3.setDouble(1,Amount1);
                            st3.setLong(2,Account1);
                            int row4 = st3.executeUpdate();
                            if(row4==1)
                            {
                                Random rand = new Random();
                                long Transaction = (rand.nextInt(1000000)+ 10000000L) * (rand.nextInt(900)+100);

                                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime now = LocalDateTime.now();

                                PreparedStatement st4 = con.prepareStatement("insert into TRANSACTION_DETAILS(TRANSACTION_ID,TRANS_DATE_TIME,TRANSACTION_TYPE,AMOUNT,ACCOUNT_NO) values(?,?,?,?,?)");
                                st4.setLong(1,Transaction);
                                st4.setTimestamp(2, Timestamp.valueOf(dtf.format(now)));
                                st4.setString(3,"Debited");
                                st4.setDouble(4,Amount1);
                                st4.setLong(5,Account1);
                                int row5 = st4.executeUpdate();
                                if(row5==1)
                                {
                                    System.out.println("Amount Debited Successfully...\n");
                                }
                            }
                        }
                        else
                        {
                            System.out.println("In sufficient Amount In Your Account !!!\n");
                        }
                    }
                    else
                    {
                        System.out.println("No Details Found On This A/C Number Please Try With Correct A/C Number...\n");
                    }
                    break;
                case 3:
                    System.out.println("Enter Your Account Number");
                    Scanner Scan1 = new Scanner(System.in);
                    long Account = Scan1.nextLong();

                    PreparedStatement st2 = con.prepareStatement("select ACCOUNT_NO from ACCOUNT_DETAILS where ACCOUNT_NO = ?");
                    st2.setLong(1,Account);
                    ResultSet row3 = st2.executeQuery();
                    int result = 0;
                    while (row3.next())
                    {
                        result++;
                    }

                    if(result==1)
                    {
                        System.out.println("\nEnter The Amount To be Deposit");
                        Scanner Scan2 = new Scanner(System.in);
                        double Amount = Scan2.nextLong();


                        PreparedStatement st3 = con.prepareStatement("update ACCOUNT_DETAILS set BALANCE = BALANCE+? where ACCOUNT_NO = ?");
                        st3.setDouble(1,Amount);
                        st3.setLong(2,Account);
                        int row4 = st3.executeUpdate();

                        if(row4==1)
                        {
                            Random rand = new Random();
                            long Transaction = (rand.nextInt(1000000)+ 10000000L) * (rand.nextInt(900)+100);

                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();

                            PreparedStatement st4 = con.prepareStatement("insert into TRANSACTION_DETAILS(TRANSACTION_ID,TRANS_DATE_TIME,TRANSACTION_TYPE,AMOUNT,ACCOUNT_NO) values(?,?,?,?,?)");
                            st4.setLong(1,Transaction);
                            st4.setTimestamp(2, Timestamp.valueOf(dtf.format(now)));
                            st4.setString(3,"Credited");
                            st4.setDouble(4,Amount);
                            st4.setLong(5,Account);
                            int row5 = st4.executeUpdate();
                            if(row5==1)
                            {
                                System.out.println("Amount Deposited Successfully...\n");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("No Details Found On This A/C Number Please Try With Correct A/C Number...\n");
                    }
                    break;
                case 4:
                    System.out.println("Enter Your Account Number");
                    Scanner Scan4 = new Scanner(System.in);
                    long balance = Scan4.nextLong();

                    PreparedStatement st4 = con.prepareStatement("select ACCOUNT_NO from ACCOUNT_DETAILS where ACCOUNT_NO = ?");
                    st4.setLong(1,balance);
                    ResultSet row5 = st4.executeQuery();
                    int result2 = 0;
                    while (row5.next())
                    {
                        result2++;
                    }
                    if(result2==1)
                    {
                        PreparedStatement st6 = con.prepareStatement("select BALANCE from ACCOUNT_DETAILS where ACCOUNT_NO = ?");
                        st6.setLong(1,balance);
                        ResultSet row7 = st6.executeQuery();
                        while (row7.next())
                        {
                            System.out.println("Total Account Balance : "+row7.getDouble(1)+"\n");
                        }
                    }
                    else
                    {
                        System.out.println("No Details Found On This A/C Number Please Try With Correct A/C Number...\n");
                    }
                    break;
                case 5:
                    System.out.println("Enter Your Account Number");
                    Scanner Scan5 = new Scanner(System.in);
                    long Transfer = Scan5.nextLong();

                    PreparedStatement st6 = con.prepareStatement("select ACCOUNT_NO from ACCOUNT_DETAILS where ACCOUNT_NO = ?");
                    st6.setLong(1,Transfer);
                    ResultSet row7 = st6.executeQuery();
                    int result3 = 0;
                    while (row7.next())
                    {
                        result3++;
                    }

                    if(result3==1)
                    {
                        System.out.println("Enter Account Number To be Transfer Money");
                        Scanner Scan7 = new Scanner(System.in);
                        long Transfer1 = Scan5.nextLong();

                        PreparedStatement st7 = con.prepareStatement("select ACCOUNT_NO from ACCOUNT_DETAILS where ACCOUNT_NO = ?");
                        st7.setLong(1,Transfer1);
                        ResultSet row8= st7.executeQuery();
                        int result4 = 0;
                        while (row8.next())
                        {
                            result4++;
                        }
                        if(result4==1)
                        {
                            System.out.println("\nEnter The Amount To be Transfer");
                            Scanner Scan8 = new Scanner(System.in);
                            double Amount2 = Scan8.nextLong();

                            PreparedStatement st8 = con.prepareStatement("select ACCOUNT_NO,BALANCE from ACCOUNT_DETAILS where ACCOUNT_NO = ? AND BALANCE > ?");
                            st8.setLong(1, Transfer);
                            st8.setDouble(2,Amount2);
                            ResultSet row9 = st8.executeQuery();
                            int result5 = 0;
                            while (row9.next())
                            {
                                result5++;
                            }

                            if (result5 == 1)
                            {
                                PreparedStatement st9 = con.prepareStatement("update ACCOUNT_DETAILS set BALANCE = BALANCE-? where ACCOUNT_NO = ?");
                                st9.setDouble(1, Amount2);
                                st9.setLong(2, Transfer);
                                int row10 = st9.executeUpdate();

                                if (row10 == 1)
                                {
                                    PreparedStatement st10 = con.prepareStatement("update ACCOUNT_DETAILS set BALANCE = BALANCE+? where ACCOUNT_NO = ?");
                                    st10.setDouble(1,Amount2);
                                    st10.setLong(2,Transfer1);
                                    int row11 = st10.executeUpdate();

                                    if (row11 == 1)
                                    {
                                        Random rand = new Random();
                                        long Transaction3 = (rand.nextInt(1000000) + 10000000L) * (rand.nextInt(900) + 100);

                                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                        LocalDateTime now = LocalDateTime.now();

                                        PreparedStatement st11 = con.prepareStatement("insert into TRANSACTION_DETAILS(TRANSACTION_ID,TRANS_DATE_TIME,TRANSACTION_TYPE,AMOUNT,ACCOUNT_NO) values(?,?,?,?,?)");
                                        st11.setLong(1, Transaction3);
                                        st11.setTimestamp(2, Timestamp.valueOf(dtf.format(now)));
                                        st11.setString(3, "Fund Transfer");
                                        st11.setDouble(4, Amount2);
                                        st11.setLong(5, Transfer);
                                        int row12 = st11.executeUpdate();

                                        if(row12==1)
                                        {
                                            PreparedStatement st12 = con.prepareStatement("insert into fund_transfer(TRANSACTION_ID,TRANSFER_TO_ACCOUNT) values(?,?)");
                                            st12.setLong(1, Transaction3);
                                            st12.setLong(2, Transfer1);
                                            int row13 = st12.executeUpdate();

                                            if (row13 == 1)
                                            {
                                                System.out.println("Amount Transferred Successfully...\n");
                                            }
                                        }
                                    }
                                }
                            }
                            else
                            {
                                System.out.println("In sufficient Amount In Your Account !!!\n");
                            }
                        }
                        else
                        {
                            System.out.println("\nEnter The Amount To be Transfer");
                            Scanner Scan13 = new Scanner(System.in);
                            double Amount3 = Scan13.nextLong();

                            PreparedStatement st13 = con.prepareStatement("select ACCOUNT_NO,BALANCE from ACCOUNT_DETAILS where ACCOUNT_NO = ? AND BALANCE > ?");
                            st13.setLong(1, Transfer);
                            st13.setDouble(2, Amount3);
                            ResultSet row13 = st13.executeQuery();
                            int result6 = 0;
                            while (row13.next())
                            {
                                result6++;
                            }

                            if (result6 == 1)
                            {
                                PreparedStatement st14 = con.prepareStatement("update ACCOUNT_DETAILS set BALANCE = BALANCE-? where ACCOUNT_NO = ?");
                                st14.setDouble(1, Amount3);
                                st14.setLong(2, Transfer);
                                int row14 = st14.executeUpdate();

                                if(row14==1)
                                {
                                    Random rand = new Random();
                                    long Transaction4 = (rand.nextInt(1000000) + 10000000L) * (rand.nextInt(900) + 100);

                                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                    LocalDateTime now = LocalDateTime.now();

                                    PreparedStatement st15 = con.prepareStatement("insert into TRANSACTION_DETAILS(TRANSACTION_ID,TRANS_DATE_TIME,TRANSACTION_TYPE,AMOUNT,ACCOUNT_NO) values(?,?,?,?,?)");
                                    st15.setLong(1, Transaction4);
                                    st15.setTimestamp(2, Timestamp.valueOf(dtf.format(now)));
                                    st15.setString(3, "Fund Transfer");
                                    st15.setDouble(4, Amount3);
                                    st15.setLong(5, Transfer);
                                    int row15 = st15.executeUpdate();

                                    if(row15==1)
                                    {
                                        PreparedStatement st16 = con.prepareStatement("insert into fund_transfer(TRANSACTION_ID,TRANSFER_TO_ACCOUNT) values(?,?)");
                                        st16.setLong(1, Transaction4);
                                        st16.setLong(2, Transfer1);
                                        int row16 = st16.executeUpdate();

                                        if (row16 == 1)
                                        {
                                            System.out.println("Amount Transferred Successfully...\n");
                                        }
                                    }
                                }

                            } else
                            {
                                System.out.println("In sufficient Amount In Your Account !!!\n");
                            }
                        }
                    }
                    else
                    {
                        System.out.println("No Details Found On This A/C Number Please Try With Correct A/C Number...\n");
                    }
                    break;
                case 6:
                    PreparedStatement st18 = con.prepareStatement("select ACCOUNT_NO,ACCOUNT_TYPE from ACCOUNT_DETAILS where ACCOUNT_TYPE = ?");
                    st18.setString(1,"Save Account");
                    ResultSet row18 = st18.executeQuery();

                    while (row18.next())
                    {
                        PreparedStatement st19 = con.prepareStatement("select AC_CREATED_DATE,BALANCE from ACCOUNT_DETAILS where ACCOUNT_NO = ?");
                        st19.setLong(1,row18.getLong(1));
                        ResultSet row19 = st19.executeQuery();

                        while (row19.next())
                        {
                            PreparedStatement st22 = con.prepareStatement("select last_Updated_Date,Account_No from interest_table where Account_No = ? order by last_Updated_Date desc limit 1");
                            st22.setLong(1,row18.getLong(1));
                            ResultSet row22 = st22.executeQuery();

                            int no = 0;
                            while (row22.next())
                            {

                                Date start_date = row22.getDate(1);

                                long millis = System.currentTimeMillis();
                                Date date2 = new Date(millis);

                                long diffInMillies = Math.abs(date2.getTime() - start_date.getTime());
                                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                                if(diff>=365)
                                {
                                    PreparedStatement st20 = con.prepareStatement("update account_details set BALANCE = BALANCE+(BALANCE*0.025) where ACCOUNT_NO = ?");
                                    st20.setLong(1, row18.getLong(1));
                                    int row20 = st20.executeUpdate();

                                    if(row20==1)
                                    {
                                        Random rand = new Random();
                                        long Transaction4 = (rand.nextInt(1000000) + 10000000000L) * (rand.nextInt(900) + 100);

                                        PreparedStatement st21 = con.prepareStatement("insert into interest_table(Interest_Updated_Id,last_Updated_Date,Amount_added,Account_No) values(?,?,?,?)");
                                        st21.setLong(1,Transaction4);
                                        st21.setDate(2,date2);
                                        double amt = (row19.getDouble(2)*0.025);
                                        st21.setDouble(3,amt);
                                        st21.setLong(4,row18.getLong(1));
                                        int row21 = st21.executeUpdate();
                                        if(row21==1)
                                        {
                                            System.out.println("Interest Amount Updated Successfully...\n");
                                        }
                                    }
                                }
                                no++;
                            }

                            if(no==0)
                            {
                                Date start_date = row19.getDate(1);

                                long millis = System.currentTimeMillis();
                                Date date2 = new Date(millis);

                                long diffInMillies = Math.abs(date2.getTime() - start_date.getTime());
                                long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

                                if(diff>=365)
                                {
                                    PreparedStatement st20 = con.prepareStatement("update account_details set BALANCE = BALANCE+(BALANCE*0.025) where ACCOUNT_NO = ?");
                                    st20.setLong(1, row18.getLong(1));
                                    int row20 = st20.executeUpdate();

                                    if(row20==1)
                                    {
                                        Random rand = new Random();
                                        long Transaction4 = (rand.nextInt(1000000) + 10000000000L) * (rand.nextInt(900) + 100);

                                        PreparedStatement st21 = con.prepareStatement("insert into interest_table(Interest_Updated_Id,last_Updated_Date,Amount_added,Account_No) values(?,?,?,?)");
                                        st21.setLong(1,Transaction4);
                                        st21.setDate(2,date2);
                                        double amt = (row19.getDouble(2)*0.025);
                                        st21.setDouble(3,amt);
                                        st21.setLong(4,row18.getLong(1));
                                        int row21 = st21.executeUpdate();
                                        if(row21==1)
                                        {
                                            System.out.println("Interest Amount Updated Successfully...\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 7:
                    System.out.println("------------THANK YOU FOR USING IDBC BANK ONLINE PORTAL--------------");
                    return;
                default :
                    System.out.println("-------INVALID INPUT------\n");

            }
        }
    }
}