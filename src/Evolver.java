// gpjpp example program
// Copyright (c) 1997, Kim Kokkonen
//
// This program is free software; you can redistribute it and/or 
// modify it under the terms of version 2 of the GNU General Public 
// License as published by the Free Software Foundation.
// 
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
// 
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// Send comments, suggestions, problems to kimk@turbopower.com

import gpjpp.*;

class Evolver extends GPRun {

    //must override GPRun.createVariables to return lawn-specific variables
    protected GPVariables createVariables() { 
        return new TradeVariables(); 
    }

    //must override GPRun.createNodeSet to return 
    //  initialized set of functions & terminals
    protected GPAdfNodeSet createNodeSet(GPVariables cfg) {
        GPAdfNodeSet adfNs = new GPAdfNodeSet(1);
        GPNodeSet ns0 = new GPNodeSet(29);
        adfNs.put(0, ns0);

        //MAIN TREE
        ns0.putNode(new GPNode(Trader.ZERO, "zero"));
        ns0.putNode(new GPNode(Trader.ONE, "one"));
        ns0.putNode(new GPNode(Trader.TWO, "two"));
        ns0.putNode(new GPNode(Trader.INC, "inc", 1));
        ns0.putNode(new GPNode(Trader.DEC, "dec", 1));
        ns0.putNode(new GPNode(Trader.ADD, "add", 2));
        ns0.putNode(new GPNode(Trader.SUB, "sub", 2));
        ns0.putNode(new GPNode(Trader.MAX, "max", 2));
        ns0.putNode(new GPNode(Trader.MIN, "min", 2));
        ns0.putNode(new GPNode(Trader.ITE, "ite", 4));
        ns0.putNode(new GPNode(Trader.RANDOM, "random"));
        ns0.putNode(new GPNode(Trader.PRICE, "price"));
        ns0.putNode(new GPNode(Trader.INVERT, "invert", 1));
        ns0.putNode(new GPNode(Trader.PAST1M, "past1m"));
        ns0.putNode(new GPNode(Trader.AVG1M, "avg1m"));
        ns0.putNode(new GPNode(Trader.AVG1W, "avg1w"));
        ns0.putNode(new GPNode(Trader.DPS, "dps"));
        ns0.putNode(new GPNode(Trader.BPS, "bps"));
        ns0.putNode(new GPNode(Trader.SPS, "sps"));
        ns0.putNode(new GPNode(Trader.ROE, "roe"));
        ns0.putNode(new GPNode(Trader.NETF, "netf"));
        ns0.putNode(new GPNode(Trader.NONCI, "non-ci"));
        ns0.putNode(new GPNode(Trader.EBITDA, "ebitda"));
        ns0.putNode(new GPNode(Trader.REVENUE, "rev"));
        ns0.putNode(new GPNode(Trader.OPINC, "oper-inc"));
        ns0.putNode(new GPNode(Trader.NETINC, "net-inc"));
        ns0.putNode(new GPNode(Trader.OPCASH, "oper-cash"));
        ns0.putNode(new GPNode(Trader.FREECASH, "free cash"));
        ns0.putNode(new GPNode(Trader.NETCAP, "netcap"));
        
        return adfNs;
    }

    //must override GPRun.createPopulation to return 
    //  lawn-specific population
    protected GPPopulation createPopulation(GPVariables cfg, 
        GPAdfNodeSet adfNs) {
        return new TradePopulation(cfg, adfNs);
    }

    //construct this test case
    Evolver(String baseName) { super(baseName, true); }

    //main application function
    public static void main(String[] args) {

        //compute base file name from command line parameter
        String baseName;
        if (args.length == 1)
            baseName = args[0];
        else
            baseName = "trader";

        //construct the test case
        Evolver test = new Evolver(baseName);

        //run the test
        test.run();

        //make sure all threads are killed
        System.exit(0);
    }
}
