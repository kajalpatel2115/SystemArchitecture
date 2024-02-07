import fileinput
index_register = [0,0,0,0]

def inp(statement):
    return input(statement)

def binary2octal(input_binary, length):
    #input_binary type is a string, the return val is an octal number, data type is string
    decimal_val = int(input_binary, 2)
    oct_val = oct(decimal_val)[2:]
    if (length-len(oct_val)) >= 0:
        offsetZERO = (length-len(oct_val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + oct_val

def decimal2binary(x, length):
    # x is a int value
    val = bin(x)[2:]
    if (length-len(val)) >= 0:
        offsetZERO = (length-len(val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + val

def decimal2octal(x, length):
    # x is a int value
    val = oct(x)[2:]
    if (length-len(val)) >= 0:
        offsetZERO = (length-len(val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + val

def process_binstr_register(opcode, content_list):
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
    bin_opcode = decimal2binary(opcode, 6)
    decimal_Rx = int(content_list[0])
    decimal_Ry = int(content_list[1])
    bin_Rx = decimal2binary(decimal_Rx, 2)
    bin_Ry = decimal2binary(decimal_Ry, 2)
    bin_str = bin_opcode + bin_Rx + bin_Ry + "000000"
    return bin_str

def process_binstr_count(opcode, content_list):
    bin_opcode = decimal2binary(opcode, 6)
    decimal_R = int(content_list[0])
    decimal_count = int(content_list[1])
    decimal_LR = int(content_list[2])
    decimal_AL = int(content_list[3])
    bin_R = decimal2binary(decimal_R, 2)
    bin_AL = decimal2binary(decimal_AL, 1)
    bin_LR = decimal2binary(decimal_LR, 1)
    bin_count = decimal2binary(decimal_count, 4)
    bin_str = bin_opcode + bin_R + bin_AL + bin_LR + "00" + bin_count
    return bin_str

def process_binstr_IO(opcode, content_list):
    bin_opcode = decimal2binary(opcode, 6)
    decimal_R = int(content_list[0])
    decimal_devid = int(content_list[1])
    bin_R = decimal2binary(decimal_R, 2)
    bin_devid = decimal2binary(decimal_devid, 5)
    bin_str = bin_opcode + bin_R + "000" + bin_devid
    return bin_str

def process_str(input_str):
    opcode = 0
    if input_str == 'HLT':
        print('HLT')
        return 'HLT'
    if (len(input_str.split()) != 2):
        print("Invalid input string.")
        return
    op = input_str.split()[0]
    content = input_str.split()[1]
    con_value = content.split(',')
    print("content: " , content)
    print("con_value: ", con_value, type(con_value))
    if op == "LOC":
        opcode = 0
        return "LOC", int(con_value[0])
    elif op == "Data":
        opcode = 0
        if con_value[0] == 'End':
            con_value[0] = 1024
        return "Data", decimal2octal(int(con_value[0]), 6)
    elif op == "LDR":
        opcode = 1
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "LDR", binary2octal(bin_str, 6)
    elif op == "STR":
        opcode = 2
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "STR", binary2octal(bin_str, 6)
    elif op == "LDA":
        opcode = 3
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "LDA", binary2octal(bin_str, 6)
    elif op == "LDX":
        opcode = 4
        if len(con_value) == 2:
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "LDX", binary2octal(bin_str, 6)
    elif op == "STX":
        opcode = 5
        if len(con_value) == 2:
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "STX", binary2octal(bin_str, 6)
    # the code below is not available now, only draft.
    elif op == "JZ":
        opcode = 6
        if len(con_value) == 2:
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "JZ", binary2octal(bin_str, 6)
    elif op == "JNE":
        opcode = 7
        if len(con_value) == 2:
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "JNE", binary2octal(bin_str, 6)
    elif op == "JCC": # todo
        opcode = 8
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "JCC", binary2octal(bin_str, 6)
    elif op == "JMA":
        opcode = 9
        if len(con_value) == 2:
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "JMA", binary2octal(bin_str, 6)
    elif op == "JSR": #todo
        opcode = 10
        if len(con_value) == 2:
            con_value.append(0)
        con_value = [0] + con_value
        bin_str = process_binstr_register(opcode, con_value)
        return "JSR", binary2octal(bin_str, 6)
    elif op == "RFS": #todo
        opcode = 11
        con_value = [0, 0] + con_value + [0]
        bin_str = process_binstr_register(opcode, con_value)
        return "RFS", binary2octal(bin_str, 6)
    elif op == "SOB":
        opcode = 12
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "SOB", binary2octal(bin_str, 6)
    elif op == "JGE":
        opcode = 13
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "JGE", binary2octal(bin_str, 6)
    elif op == "AMR":
        opcode = 14
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "AMR", binary2octal(bin_str, 6)
    elif op == "SMR":
        opcode = 15
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "SMR", binary2octal(bin_str, 6)
    elif op == "AIR": # TODO
        opcode = 16
        con_value = con_value[0] + [0] + con_value[1] + [0]
        bin_str = process_binstr_register(opcode, con_value)
        return "AIR", binary2octal(bin_str, 6)
    elif op == "SIR":
        opcode = 17
        con_value = con_value[0] + [0] + con_value[1] + [0]
        bin_str = process_binstr_register(opcode, con_value)
        return "SIR", binary2octal(bin_str, 6)
    elif op == "MLT":
        opcode = 18
        bin_str = process_binstr_logic(opcode, con_value)
        return "MLT", binary2octal(bin_str, 6)
    elif op == "DVD":
        opcode = 19
        bin_str = process_binstr_logic(opcode, con_value)
        return "DVD", binary2octal(bin_str, 6)
    elif op == "TRR":
        opcode = 20
        bin_str = process_binstr_logic(opcode, con_value)
        return "TRR", binary2octal(bin_str, 6)
    elif op == "AND":
        opcode = 21
        bin_str = process_binstr_logic(opcode, con_value)
        return "AND", binary2octal(bin_str, 6)
    elif op == "ORR":
        opcode = 22
        bin_str = process_binstr_logic(opcode, con_value)
        return "ORR", binary2octal(bin_str, 6)
    elif op == "NOT":
        opcode = 23
        bin_str = process_binstr_logic(opcode, con_value + [0])
        return "NOT", binary2octal(bin_str, 6)
    elif op == "SRC":
        opcode = 24
        bin_str = process_binstr_count(opcode, con_value)
        return "SRC", binary2octal(bin_str, 6)
    elif op == "RRC":
        opcode = 25
        bin_str = process_binstr_count(opcode, con_value)
        return "RRC", binary2octal(bin_str, 6)
    elif op == "IN":
        opcode = 26
        bin_str = process_binstr_IO(opcode, con_value)
        return "IN", binary2octal(bin_str, 6)
    elif op == "OUT":
        opcode = 27
        bin_str = process_binstr_IO(opcode, con_value)
        return "OUT", binary2octal(bin_str, 6)
    elif op == "CHK":
        opcode = 28
        bin_str = process_binstr_IO(opcode, con_value)
        return "CHK", binary2octal(bin_str, 6)
    elif op == "FADD":
        opcode = 29
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "FADD", binary2octal(bin_str, 6)
    elif op == "FSUB":
        opcode = 30
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "FSUB", binary2octal(bin_str, 6)
    elif op == "VADD":
        opcode = 31
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "VADD", binary2octal(bin_str, 6)
    elif op == "VSUB":
        opcode = 32
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "VSUB", binary2octal(bin_str, 6)
    elif op == "CNVRT":
        opcode = 33
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "CNVRT", binary2octal(bin_str, 6)
    elif op == "LDFR":
        opcode = 34
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "LDFR", binary2octal(bin_str, 6)
    elif op == "STFR":
        opcode = 35
        if len(con_value) == 3:
            con_value.append(0)
        bin_str = process_binstr_register(opcode, con_value)
        return "STFR", binary2octal(bin_str, 6)
    elif op == "SETCCE":
        opcode = 36
        con_value = con_value + [0, 0, 0]
        bin_str = process_binstr_register(opcode, con_value)
        return "SETCCE", binary2octal(bin_str, 6)
    elif op == "TRAP":
        opcode = 37
        con_value = [0, 0] + con_value + [0]
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
        output_str, LOC_pointer = process(line, LOC_pointer)
        f_out.write(output_str + '\n')
    f_out.close()
    
