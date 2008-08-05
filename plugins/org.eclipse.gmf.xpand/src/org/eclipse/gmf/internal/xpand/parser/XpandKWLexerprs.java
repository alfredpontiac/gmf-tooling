/*
 * Copyright (c) 2006, 2008 Borland Software Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     committers of openArchitectureWare - Xpand language syntax
 *     Artem Tikhomirov (Borland) - LALR grammar
 *                                - Migration to OCL expressions
 */
package org.eclipse.gmf.internal.xpand.parser;

public class XpandKWLexerprs implements lpg.lpgjavaruntime.ParseTable, XpandKWLexersym {

    public interface IsKeyword {
        public final static byte isKeyword[] = {0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0
        };
    };
    public final static byte isKeyword[] = IsKeyword.isKeyword;
    public final boolean isKeyword(int index) { return isKeyword[index] != 0; }

    public interface BaseCheck {
        public final static byte baseCheck[] = {0,
            4,3,3,4,4,7,7,10,3,6,
            4,2,4,4,5,3,2,3,3,7,
            3,2,4,5,3,3,8,10,10,7,
            6,6,8,3,3,7,6,6,13,8,
            7,11,11,9,8,14,12,12,12,6,
            7,16,4,7,5,6,7,7,10,4,
            10,4,4,6,9,6,9,6,9,5,
            6,3,9,2,8,7,10,4,7,2,
            6,4,5,3,6,7,6,4,2,7,
            10
        };
    };
    public final static byte baseCheck[] = BaseCheck.baseCheck;
    public final int baseCheck(int index) { return baseCheck[index]; }
    public final static byte rhs[] = baseCheck;
    public final int rhs(int index) { return rhs[index]; };

    public interface BaseAction {
        public final static char baseAction[] = {
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,1,1,
            1,1,1,94,110,150,158,86,99,105,
            27,164,172,175,35,176,106,24,51,82,
            117,182,109,178,63,94,111,184,75,125,
            180,95,58,120,186,36,168,188,70,189,
            190,194,192,196,127,197,201,202,206,198,
            134,208,209,214,210,76,217,218,221,222,
            224,129,225,226,234,235,237,240,241,244,
            250,243,253,43,254,257,135,255,259,261,
            260,137,263,264,265,266,268,278,271,142,
            276,280,274,285,287,281,288,289,295,291,
            26,294,297,298,303,307,308,310,311,312,
            315,74,321,318,325,323,327,328,329,331,
            332,334,339,333,144,337,335,348,346,352,
            353,355,145,357,358,360,364,365,366,369,
            370,371,375,379,147,380,372,113,376,383,
            382,384,386,389,388,155,391,392,401,402,
            404,406,407,409,408,410,413,417,414,416,
            423,424,425,433,437,428,426,442,444,445,
            440,448,449,451,452,455,456,457,459,463,
            461,465,469,466,470,468,476,477,481,478,
            482,485,484,487,488,489,490,493,491,492,
            494,504,510,496,517,495,512,519,520,521,
            523,525,526,528,530,531,532,535,539,49,
            533,541,548,546,549,550,552,553,554,559,
            562,560,565,570,555,566,573,572,574,575,
            576,580,581,586,588,587,593,594,589,601,
            603,602,608,609,611,612,613,614,618,619,
            621,622,624,628,629,632,634,635,638,639,
            640,643,642,646,641,649,651,661,647,652,
            660,654,665,669,153,670,671,674,676,675,
            677,678,684,682,686,687,688,689,690,692,
            697,694,698,705,700,702,710,714,721,723,
            717,725,706,727,730,732,733,734,735,737,
            740,741,745,738,746,749,752,753,751,756,
            764,758,767,759,768,771,773,777,780,776,
            782,783,788,784,790,792,794,795,797,796,
            452,452
        };
    };
    public final static char baseAction[] = BaseAction.baseAction;
    public final int baseAction(int index) { return baseAction[index]; }
    public final static char lhs[] = baseAction;
    public final int lhs(int index) { return lhs[index]; };

    public interface TermCheck {
        public final static byte termCheck[] = {0,
            0,1,2,3,4,5,6,7,8,9,
            10,11,12,13,14,15,16,0,18,19,
            20,21,22,0,24,0,0,27,5,29,
            30,31,32,33,0,0,13,11,4,39,
            15,16,0,17,21,20,12,12,0,24,
            0,9,27,27,20,30,6,0,24,2,
            12,4,0,13,16,17,4,10,26,0,
            8,45,38,0,0,0,19,3,4,31,
            18,0,1,2,22,0,5,12,15,16,
            21,43,35,0,0,2,3,4,0,14,
            6,16,8,5,0,0,32,13,0,0,
            0,38,0,8,16,11,0,44,8,0,
            1,13,6,18,0,21,0,3,0,13,
            2,23,13,0,0,2,0,13,12,3,
            4,0,8,0,0,33,0,1,7,0,
            40,7,0,19,0,46,30,0,15,16,
            11,28,5,0,36,19,22,0,14,28,
            16,0,1,21,0,0,1,0,4,0,
            28,0,1,0,1,0,23,0,0,0,
            13,0,13,0,27,0,0,0,11,14,
            0,0,11,14,3,0,6,0,0,0,
            5,3,24,0,21,20,0,0,22,3,
            0,0,9,0,0,0,17,30,3,0,
            13,8,8,0,0,2,0,3,2,0,
            0,1,0,0,2,25,3,8,41,0,
            1,30,0,0,0,3,0,1,0,0,
            0,7,0,0,0,0,1,0,10,9,
            0,9,13,0,10,0,23,0,1,0,
            0,18,3,8,0,12,0,0,0,5,
            0,5,22,0,0,15,0,0,31,5,
            3,5,0,16,14,3,0,0,15,0,
            0,0,1,7,0,1,6,0,11,31,
            0,1,0,14,0,3,0,0,0,3,
            0,0,0,0,0,7,0,20,0,15,
            8,3,15,10,8,0,15,0,1,4,
            16,0,0,23,0,1,0,0,7,0,
            3,2,10,0,0,0,10,2,0,0,
            0,0,4,3,0,0,2,8,0,0,
            2,0,0,0,5,0,15,0,0,26,
            0,0,17,5,11,5,11,16,34,17,
            0,0,11,0,1,0,0,0,0,0,
            3,24,0,0,1,0,0,17,17,11,
            4,15,0,0,0,0,4,0,13,4,
            18,8,0,1,25,11,0,1,11,0,
            35,0,1,0,0,1,3,0,0,10,
            0,0,4,2,0,0,0,10,0,1,
            0,6,0,1,0,0,6,0,0,0,
            1,7,7,19,6,0,0,0,28,23,
            0,0,5,0,0,18,0,0,0,0,
            0,0,0,0,0,0,2,14,12,11,
            7,20,7,0,29,29,14,27,24,0,
            20,0,21,24,27,12,0,1,0,0,
            0,12,0,1,0,0,7,0,10,0,
            0,0,0,6,0,4,12,3,0,28,
            0,1,10,18,15,0,26,0,0,0,
            12,0,0,0,0,25,8,10,0,0,
            2,0,1,18,0,0,2,18,9,0,
            5,0,0,0,0,0,5,25,5,0,
            0,12,2,32,12,0,0,0,0,36,
            16,37,0,0,8,16,9,9,6,14,
            0,0,0,10,2,4,31,0,0,9,
            0,0,0,0,1,4,4,0,0,12,
            0,0,2,0,1,7,18,0,0,2,
            2,0,1,0,0,1,26,0,0,0,
            0,0,0,26,5,0,0,10,0,1,
            0,0,6,0,14,34,23,15,17,0,
            0,11,17,3,0,1,7,14,0,0,
            0,33,21,0,0,0,0,0,9,4,
            6,0,6,0,1,0,0,0,0,0,
            22,0,5,0,17,22,0,0,1,0,
            9,0,1,7,0,0,17,26,20,0,
            11,2,42,0,29,29,0,13,25,6,
            0,1,0,1,0,9,0,1,4,0,
            25,0,0,0,0,1,0,0,2,0,
            0,12,9,4,0,0,6,3,0,17,
            0,0,0,1,0,0,19,0,0,14,
            10,6,14,0,7,2,0,0,37,18,
            0,1,0,7,2,0,0,19,2,0,
            1,0,0,0,9,4,19,0,1,0,
            1,0,10,0,0,0,0,0,3,0,
            9,8,6,9,0,0,23,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0,0,0,0,0,0,0,0,0,0,
            0
        };
    };
    public final static byte termCheck[] = TermCheck.termCheck;
    public final int termCheck(int index) { return termCheck[index]; }

    public interface TermAction {
        public final static char termAction[] = {0,
            452,118,116,111,112,100,115,122,105,117,
            123,102,101,114,106,99,104,452,119,110,
            98,109,121,452,97,452,452,96,144,107,
            95,103,113,108,452,452,146,132,140,120,
            247,244,452,133,145,246,138,536,452,245,
            452,468,243,131,541,242,148,452,532,177,
            358,179,452,147,355,357,160,176,486,452,
            469,134,139,452,452,452,464,168,169,356,
            159,452,151,150,158,452,149,204,260,257,
            185,359,178,452,452,161,162,163,452,127,
            173,126,175,129,452,452,167,174,452,452,
            452,258,452,143,128,130,452,259,164,452,
            181,156,153,142,452,526,452,170,452,152,
            477,155,180,452,452,199,452,171,192,224,
            225,22,219,452,452,288,452,285,232,452,
            165,275,452,461,452,451,191,452,270,271,
            124,198,125,452,209,453,276,452,295,454,
            294,452,136,406,452,452,141,452,137,452,
            407,452,154,452,166,452,135,452,452,452,
            157,452,172,452,183,452,452,452,184,182,
            452,452,189,187,195,452,194,452,452,452,
            196,201,186,452,188,190,452,452,193,205,
            452,452,202,452,452,452,203,197,211,452,
            206,208,210,452,452,473,452,212,471,452,
            452,213,452,452,215,478,214,470,200,452,
            487,207,452,452,452,216,452,218,452,452,
            452,220,452,452,452,452,455,452,221,222,
            452,226,223,452,228,452,217,452,230,452,
            452,227,234,233,452,235,72,452,452,530,
            452,236,231,452,452,237,452,452,229,240,
            250,249,452,238,241,505,452,452,248,452,
            452,452,255,251,452,256,254,452,252,239,
            452,261,452,253,452,263,452,452,452,264,
            452,452,452,452,452,266,452,540,452,262,
            515,512,265,269,514,452,268,452,475,465,
            272,452,452,267,452,466,452,452,273,452,
            278,279,274,452,452,452,277,456,452,452,
            452,452,281,283,452,452,463,282,452,452,
            284,82,452,452,286,452,287,452,452,457,
            452,452,289,293,522,296,292,291,280,290,
            452,452,297,452,507,452,452,452,452,452,
            303,535,452,452,307,452,452,298,299,302,
            306,301,452,452,452,452,309,452,308,315,
            305,310,452,312,304,311,452,313,314,452,
            300,452,316,452,452,476,317,452,452,318,
            452,452,320,322,452,452,452,319,452,325,
            452,323,452,326,452,452,327,452,452,452,
            331,328,330,467,329,452,452,452,321,324,
            452,452,520,452,452,332,452,452,452,452,
            452,452,452,452,452,452,490,336,537,338,
            342,518,344,452,333,334,340,335,533,452,
            523,452,341,339,337,343,452,345,452,452,
            452,516,452,349,452,452,348,452,347,452,
            452,452,452,351,452,353,539,483,452,346,
            452,462,360,350,352,452,508,452,452,452,
            354,452,452,452,452,502,362,484,452,452,
            367,452,368,361,452,452,489,363,369,452,
            542,452,452,452,452,452,531,365,371,452,
            452,538,375,364,370,452,452,452,452,366,
            372,528,452,452,503,374,510,509,377,376,
            452,452,452,378,380,506,373,452,452,379,
            452,452,452,452,386,384,385,452,452,381,
            452,452,389,452,493,388,382,452,452,391,
            458,452,459,452,452,482,383,452,452,452,
            452,452,452,387,394,452,452,472,452,399,
            452,452,401,452,398,390,392,395,396,452,
            452,527,397,400,452,479,403,404,452,452,
            452,393,402,452,452,452,452,36,408,410,
            411,452,412,452,485,452,452,452,452,452,
            405,452,521,452,413,409,452,452,419,452,
            416,452,496,417,452,452,517,492,519,452,
            525,421,497,452,414,415,452,420,418,422,
            452,423,452,424,452,425,452,428,426,452,
            427,452,452,452,452,511,452,452,481,452,
            452,543,513,480,452,452,430,431,452,429,
            452,452,452,460,452,452,432,452,452,433,
            436,437,434,452,439,438,452,452,529,435,
            452,441,452,440,442,452,452,495,443,452,
            500,452,452,452,499,444,494,452,445,452,
            447,452,501,452,452,452,452,452,504,452,
            491,448,449,498,452,452,446
        };
    };
    public final static char termAction[] = TermAction.termAction;
    public final int termAction(int index) { return termAction[index]; }
    public final int asb(int index) { return 0; }
    public final int asr(int index) { return 0; }
    public final int nasb(int index) { return 0; }
    public final int nasr(int index) { return 0; }
    public final int terminalIndex(int index) { return 0; }
    public final int nonterminalIndex(int index) { return 0; }
    public final int scopePrefix(int index) { return 0;}
    public final int scopeSuffix(int index) { return 0;}
    public final int scopeLhs(int index) { return 0;}
    public final int scopeLa(int index) { return 0;}
    public final int scopeStateSet(int index) { return 0;}
    public final int scopeRhs(int index) { return 0;}
    public final int scopeState(int index) { return 0;}
    public final int inSymb(int index) { return 0;}
    public final String name(int index) { return null; }
    public final int getErrorSymbol() { return 0; }
    public final int getScopeUbound() { return 0; }
    public final int getScopeSize() { return 0; }
    public final int getMaxNameLength() { return 0; }

    public final static int
           NUM_STATES        = 357,
           NT_OFFSET         = 54,
           LA_STATE_OFFSET   = 543,
           MAX_LA            = 1,
           NUM_RULES         = 91,
           NUM_NONTERMINALS  = 2,
           NUM_SYMBOLS       = 56,
           SEGMENT_SIZE      = 8192,
           START_STATE       = 92,
           IDENTIFIER_SYMBOL = 0,
           EOFT_SYMBOL       = 46,
           EOLT_SYMBOL       = 55,
           ACCEPT_ACTION     = 451,
           ERROR_ACTION      = 452;

    public final static boolean BACKTRACK = false;

    public final int getNumStates() { return NUM_STATES; }
    public final int getNtOffset() { return NT_OFFSET; }
    public final int getLaStateOffset() { return LA_STATE_OFFSET; }
    public final int getMaxLa() { return MAX_LA; }
    public final int getNumRules() { return NUM_RULES; }
    public final int getNumNonterminals() { return NUM_NONTERMINALS; }
    public final int getNumSymbols() { return NUM_SYMBOLS; }
    public final int getSegmentSize() { return SEGMENT_SIZE; }
    public final int getStartState() { return START_STATE; }
    public final int getStartSymbol() { return lhs[0]; }
    public final int getIdentifierSymbol() { return IDENTIFIER_SYMBOL; }
    public final int getEoftSymbol() { return EOFT_SYMBOL; }
    public final int getEoltSymbol() { return EOLT_SYMBOL; }
    public final int getAcceptAction() { return ACCEPT_ACTION; }
    public final int getErrorAction() { return ERROR_ACTION; }
    public final boolean isValidForParser() { return isValidForParser; }
    public final boolean getBacktrack() { return BACKTRACK; }

    public final int originalState(int state) { return 0; }
    public final int asi(int state) { return 0; }
    public final int nasi(int state) { return 0; }
    public final int inSymbol(int state) { return 0; }

    public final int ntAction(int state, int sym) {
        return baseAction[state + sym];
    }

    public final int tAction(int state, int sym) {
        int i = baseAction[state],
            k = i + sym;
        return termAction[termCheck[k] == sym ? k : i];
    }
    public final int lookAhead(int la_state, int sym) {
        int k = la_state + sym;
        return termAction[termCheck[k] == sym ? k : la_state];
    }
}
