#!/usr/bin/env python3
import re;
import sys;

def operand_decode(op):
    direct = re.match(r"R(\d)", op)
    indirect = re.match(r"\(R(\d)\)", op)
    predec = re.match(r"\-\(R(\d)\)", op)
    postinc = re.match(r"\(R(\d)\)\+", op)
    immediate = re.match(r"#.+", op)

    if direct:
        return 0b00000 | int(direct.group(1))
    elif indirect:
        return 0b01000 | int(indirect.group(1))
    elif predec:
        return 0b10000 | int(predec.group(1))
    elif postinc:
        return 0b11000 | int(postinc.group(1))
    elif immediate:
        return 0b11111
    else:
        print(f"INVALID : {op}")
        return -1;


def bin_encode(op, op1, op2):
    op1_bin = operand_decode(op1)
    op2_bin = operand_decode(op2)
  #//print(f"DECODE: {hex(op1_bin)} {hex(op2_bin)}")
    if op1_bin != -1 and op2_bin != -1:
        return (op << 10) | (op1_bin << 5) | (op2_bin)
    else:
        print(f"INVALID : {op} {op1}, {op2}")
        return -1

def match_ops(s):
    nullary_op = r"^([^\s]+)\s?(;.*)?$"
    unary_op = r"^([^\s]+?)\s+([^\s]+?)\s?(;.*)?$"
    binary_op = r"^([^\s]+?)\s+([^\s]+?)\s?,\s?([^\s]+?)\s?(;.*)?$"
    if re.match(nullary_op, s):
        res = re.match(nullary_op, s)
        op = res.group(1)
        #print(f"NULLARY : {res.group()}")
        #print(f"OP      : {res.group(1)}")
        #print(f"COMMENT : {res.group(2)}")
        if op == "HLT":
            return 0b0000_00_00000_00000
    elif re.match(unary_op, s):
        res = re.match(unary_op, s)
        op = res.group(1)
        op1 = res.group(2);
        if op == "RBN":
            return bin_encode(0b1110_00, op1, "R7")
        elif op == "JMP":
            return bin_encode(0b0100_01, op1, "R7")
        #print(f"UNARY   : {res.group()}")
        #print(f"OPERATOR: {res.group(1)}")
        #print(f"OPERAND : {res.group(2)}")
        #print(f"COMMENT : {res.group(3)}")
    elif re.match(binary_op, s):
        res = re.match(binary_op, s)
        op = res.group(1)
        op1 = res.group(2);
        op2 = res.group(3);
        if op == "MOV":
            return bin_encode(0b0100_00, op1, op2);
        else:
            print("INVALID : {s}");
            return -1;
    else:
        print(f"INVALID : {s}")
        return -1;


while True:
    s = input("? ");
    print(f"{match_ops(s):016b}");
    print(f"{match_ops(s):04x}");



