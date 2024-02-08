# Author: Yue Tang
import fileinput
index_register = [0,0,0,0]

def inp(statement):
    return input(statement)

def binary2octal(input_binary, length):
    #input_binary type is a string, the return val is an octal number, data type is string
    decimal_val = int(input_binary, 2)
    oct_val = oct(decimal_val)[2:]
    if (length-len(oct_val)) >= 0:
        # make return string in the same length
        offsetZERO = (length-len(oct_val))
    else:
        #check the range of value
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + oct_val

def decimal2binary(x, length):
    # x is a int value
    val = bin(x)[2:]
    if (length-len(val)) >= 0:
        # make return string in the same length
        offsetZERO = (length-len(val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + val

def decimal2octal(x, length):
    # x is a int value
    val = oct(x)[2:]
    if (length-len(val)) >= 0:
        # make return string in the same length
        offsetZERO = (length-len(val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + val

def process_binstr_register(opcode, content_list):
    # the most translator function, deal with most of instructions
    bin_opcode = decimal2binary(opcode, 6)
    decimal_R = int(content_list[0])
    decimal_IX = int(content_list[1])
    decimal_address = int(content_list[2])
    decimal_I = int(content_list[3])
    bin_R = decimal2binary(decimal_R, 2)
    bin_IX = decimal2binary(decimal_IX, 2)
    bin_address = decimal2binary(decimal_address, 5)
    bin_I = decimal2binary(decimal_I, 1)
    bin_str = bin_opcode + bin_R + bin_IX + bin_I + bin_address
    return bin_str

def process_binstr_logic(opcode, content_list):
    # translate logic instructions, including MLT, DVD, TRR, AND, ORR, NOT 
    bin_opcode = decimal2binary(opcode, 6)
    decimal_Rx = int(content_list[0])
    decimal_Ry = int(content_list[1])
    bin_Rx = decimal2binary(decimal_Rx, 2)
    bin_Ry = decimal2binary(decimal_Ry, 2)
    bin_str = bin_opcode + bin_Rx + bin_Ry + "000000" # address part is ignored
    return bin_str

def process_binstr_count(opcode, content_list):
    # translate logic instructions, including SRC, RRC 
    bin_opcode = decimal2binary(opcode, 6)
    decimal_R = int(content_list[0])
    decimal_count = int(content_list[1])
    decimal_LR = int(content_list[2])
    decimal_AL = int(content_list[3])
    bin_R = decimal2binary(decimal_R, 2)
    bin_AL = decimal2binary(decimal_AL, 1)
    bin_LR = decimal2binary(decimal_LR, 1)
    bin_count = decimal2binary(decimal_count, 4)
    bin_str = bin_opcode + bin_R + bin_AL + bin_LR + "00" + bin_count # "00" part is ignored
    return bin_str

def process_binstr_IO(opcode, content_list):
    # translate logic instructions, including IN, OUT, CHK 
    bin_opcode = decimal2binary(opcode, 6)
    decimal_R = int(content_list[0])
    decimal_devid = int(content_list[1])
    bin_R = decimal2binary(decimal_R, 2)
    bin_devid = decimal2binary(decimal_devid, 5)
    bin_str = bin_opcode + bin_R + "000" + bin_devid # "00" part is ignored
    return bin_str

def process_str(input_str):
    # process different opcode
    opcode = 0 # it's a decimal value
    if input_str == 'HLT':
        return 'HLT'
    if (len(input_str.split()) != 2):
        print("Invalid input string.")
        return
    #op: get type of instructions
    op = input_str.split()[0]
    #content: get the param string
    content = input_str.split()[1]
    #con_value: split the param string into list
    #the datatype of ele in this list is string (convert to int in orther function)
    con_value = content.split(',')
    if op == "LOC":
        # load location
        opcode = 0
        return "LOC", int(con_value[0])
    elif op == "Data":
        # load data value
        opcode = 0
        if con_value[0] == 'End':
            con_value[0] = 1024
        return "Data", decimal2octal(int(con_value[0]), 6)
    elif op == "LDR":
        # input: LDR r, x, address[,I]
        opcode = 1
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "LDR", binary2octal(bin_str, 6)
    elif op == "STR":
        # input: STR r, x, address[,I]
        opcode = 2
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "STR", binary2octal(bin_str, 6)
    elif op == "LDA":
        # input: LDA r, x, address[,I]
        opcode = 3
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "LDA", binary2octal(bin_str, 6)
    elif op == "LDX":
        # input: LDX x, address[,I]
        opcode = 4
        if len(con_value) == 2:
            # if there isn't I value, append zero
            con_value.append(0)
        con_value = [0] + con_value # add R in the beginning of params
        bin_str = process_binstr_register(opcode, con_value)
        return "LDX", binary2octal(bin_str, 6)
    elif op == "STX":
        # input: STX x, address[,I]
        opcode = 5
        if len(con_value) == 2:
            # if there isn't I value, append zero
            con_value.append(0)
        con_value = [0] + con_value # add R in the beginning of params
        bin_str = process_binstr_register(opcode, con_value)
        return "STX", binary2octal(bin_str, 6)
    elif op == "JZ":
        # input: JZ x, address[,I]
        opcode = 6
        if len(con_value) == 2:
            # if there isn't I value, append zero
            con_value.append(0)
        con_value = [0] + con_value # add R in the beginning of params
        bin_str = process_binstr_register(opcode, con_value)
        return "JZ", binary2octal(bin_str, 6)
    elif op == "JNE":
        # input: JNE x, address[,I]
        opcode = 7
        if len(con_value) == 2:
            # if there isn't I value, append zero
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "JNE", binary2octal(bin_str, 6)
    elif op == "JCC": 
        # input: JCC cc, x, address[,I
        opcode = 8 # it's a decimal number (10 as a octal number)
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "JCC", binary2octal(bin_str, 6)
    elif op == "JMA":
        # input: JMA x, address[,I]
        opcode = 9 # it's a decimal number (11 as a octal number)
        if len(con_value) == 2:
            # if there isn't I value, append zero
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "JMA", binary2octal(bin_str, 6)
    elif op == "JSR": 
        # input: JSR x, address[,I]
        opcode = 10
        if len(con_value) == 2:
            # if there isn't I value, append zero
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "JSR", binary2octal(bin_str, 6)
    elif op == "RFS":
        # input: RFS Immed
        opcode = 11
        con_value = [0, 0] + con_value + [0]
        bin_str = process_binstr_register(opcode, con_value)
        return "RFS", binary2octal(bin_str, 6)
    elif op == "SOB":
        # input: SOB r, x, address[,I]
        opcode = 12
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "SOB", binary2octal(bin_str, 6)
    elif op == "JGE":
        # input: JGE r,x, address[,I]
        opcode = 13
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "JGE", binary2octal(bin_str, 6)
    elif op == "AMR":
        # input: AMR r, x, address[,I]
        opcode = 14
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "AMR", binary2octal(bin_str, 6)
    elif op == "SMR":
        # input: SMR r, x, address[,I]
        opcode = 15
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "SMR", binary2octal(bin_str, 6)
    elif op == "AIR": # TODO
        # input: AIR r, immed
        opcode = 16
        con_value = con_value[0] + [0] + con_value[1] + [0]
        # offset the ignored params
        bin_str = process_binstr_register(opcode, con_value)
        return "AIR", binary2octal(bin_str, 6)
    elif op == "SIR":
        # input: SIR r, immed
        opcode = 17
        con_value = con_value[0] + [0] + con_value[1] + [0]
        # offset the ignored params
        bin_str = process_binstr_register(opcode, con_value)
        return "SIR", binary2octal(bin_str, 6)
    elif op == "MLT":
        # input: MLT rx,ry
        opcode = 18
        bin_str = process_binstr_logic(opcode, con_value)
        return "MLT", binary2octal(bin_str, 6)
    elif op == "DVD":
        # input: DVD rx,ry
        opcode = 19
        bin_str = process_binstr_logic(opcode, con_value)
        return "DVD", binary2octal(bin_str, 6)
    elif op == "TRR":
        # input: TRR rx,ry
        opcode = 20
        bin_str = process_binstr_logic(opcode, con_value)
        return "TRR", binary2octal(bin_str, 6)
    elif op == "AND":
        # input: AND rx,ry
        opcode = 21
        bin_str = process_binstr_logic(opcode, con_value)
        return "AND", binary2octal(bin_str, 6)
    elif op == "ORR":
        # input: ORR rx,ry
        opcode = 22
        bin_str = process_binstr_logic(opcode, con_value)
        return "ORR", binary2octal(bin_str, 6)
    elif op == "NOT":
        # input: NOT rx
        opcode = 23
        bin_str = process_binstr_logic(opcode, con_value + [0]) # offset the ignored params
        return "NOT", binary2octal(bin_str, 6)
    elif op == "SRC":
        # input: SRC r, count, L/R, A/L
        opcode = 24
        bin_str = process_binstr_count(opcode, con_value)
        return "SRC", binary2octal(bin_str, 6)
    elif op == "RRC":
        # input: RRC r, count, L/R, A/L
        opcode = 25
        bin_str = process_binstr_count(opcode, con_value)
        return "RRC", binary2octal(bin_str, 6)
    elif op == "IN":
        # input: IN r, devid
        opcode = 26
        bin_str = process_binstr_IO(opcode, con_value)
        return "IN", binary2octal(bin_str, 6)
    elif op == "OUT":
        # input: OUT r, devid
        opcode = 27
        bin_str = process_binstr_IO(opcode, con_value)
        return "OUT", binary2octal(bin_str, 6)
    elif op == "CHK":
        # input: CHK r, devid
        opcode = 28
        bin_str = process_binstr_IO(opcode, con_value)
        return "CHK", binary2octal(bin_str, 6)
    elif op == "FADD":
        # input: FADD fr, x, address[,I]
        opcode = 29
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "FADD", binary2octal(bin_str, 6)
    elif op == "FSUB":
        # input: FSUB fr, x, address[,I]
        opcode = 30
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "FSUB", binary2octal(bin_str, 6)
    elif op == "VADD":
        # input: VADD fr, x, address[,I]
        opcode = 31
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "VADD", binary2octal(bin_str, 6)
    elif op == "VSUB":
        # input: VSUB fr, x, address[,I]
        opcode = 32
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "VSUB", binary2octal(bin_str, 6)
    elif op == "CNVRT":
        # input: CNVRT fr, x, address[,I]
        opcode = 33
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "CNVRT", binary2octal(bin_str, 6)
    elif op == "LDFR":
        # input: LDFR fr, x, address[,I]
        opcode = 34
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "LDFR", binary2octal(bin_str, 6)
    elif op == "STFR":
        # input: STFR fr, x, address[,I]
        opcode = 35
        if len(con_value) == 3:
            # if there isn't I value, append zero
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "STFR", binary2octal(bin_str, 6)
    elif op == "SETCCE":
        # input: SETCCE r
        opcode = 36
        con_value = con_value + [0, 0, 0] # offset the ignored params
        bin_str = process_binstr_register(opcode, con_value)
        return "SETCCE", binary2octal(bin_str, 6)
    elif op == "TRAP":
        # input: TRAP code. TRAP code use the address part in binary numbers.
        opcode = 37
        con_value = [0, 0] + con_value + [0] # offset the ignored params
        bin_str = process_binstr_register(opcode, con_value)
        return "SETCCE", binary2octal(bin_str, 6)
    
    else:
        return "Invalid input string."

running_flag = True
# LOC_pointer is a deci



def process(in_str, LOC_pointer):
    instruction = process_str(in_str)
    if instruction[0] == 'LOC':
        LOC_format = decimal2octal(LOC_pointer, 6)
        instruction_format = decimal2octal(0, 6)
        LOC_pointer = int(instruction[1])
        output_str = LOC_format + ' ' + instruction_format
        print(output_str, LOC_pointer)
        return output_str, LOC_pointer
    elif instruction == 'HLT':
        running_flag = False
        LOC_format = decimal2octal(LOC_pointer, 6)
        instruction_format = decimal2octal(0, 6)
        output_str = LOC_format + ' ' + instruction_format
        print(output_str, LOC_pointer)
        return output_str, LOC_pointer
    else:
        LOC_format = decimal2octal(LOC_pointer, 6)
        print(LOC_format, ' ', instruction[1])
        output_str = LOC_format + ' ' + instruction[1]
        LOC_pointer = LOC_pointer + 1
        print(output_str, LOC_pointer)
        return output_str, LOC_pointer

if __name__ == "__main__":
    LOC_pointer = 0
    f_out = open("output.txt", "w")
    filename = 'input.txt'
    for line in fileinput.input(files=filename):
        # read input file line by line.
        output_str, LOC_pointer = process(line, LOC_pointer)
        f_out.write(output_str + '\n')
    f_out.close()
    
