bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf,gets              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import scanf msvcrt.dll
import printf msvcrt.dll
import gets msvcrt.dll

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    s dd 15,0FFFFFFFFh,30,25
    len_sir equ ($-s)/4
    d times len_sir*2 dw 0
    print db 'Cititi k: ',0
    readformat db '%d',0
    afisare db '%d / %d = %d',0
    mesaj db 'Ati citit k= %d',0
    k db 5
    nr dd 0
    sum dd 0
    cat dd 0
    impartitor dw 10
    newline db 10,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
        
        

        push dword print
        call [printf]
        add esp,4*1
        
        
        push dword k
        push dword readformat
        call [scanf]
        add esp,4*2
        
        push dword [k]
        push dword mesaj
        call [printf]
        add esp,4*2
        
        push newline
        call [printf]
        add esp,4*1
        
        
        mov eax,0
        mov ebx,0
        mov edx,0
        mov ecx,len_sir
        mov esi,0
        mov edi,0
        
        
        repeta:
        mov eax,[s+esi]
        cmp eax,0
        JG word1
        JLE final
        
        word1:
        mov eax,0
        mov ax,word[s+esi]
        cwd
        idiv word[impartitor]
        cmp dx,[k]
        JE adauga1
        JNE word2
        
        word2:
        mov eax,0
        mov ax,word[s+esi+2]
        cwd
        idiv word[impartitor]
        cmp dx,[k]
        JE adauga2
        JNE final
        
        
        
        adauga1:
        mov ax,[s+esi]
        mov [d+edi],ax
        
        add ebx,1
          ; xchg [nr],bx
         ; ; add ecx,1
          ; xchg bx,[nr]
        add edi,2
        jmp word2
        
        
        adauga2:
        mov ax,[s+esi+2]
        mov [d+edi],ax
        
        add ebx,1
          ; xchg [nr],bx
        ; ; ; add ecx,1
          ; xchg bx,[nr]
        add edi,2
        jmp final
        
   
        final:
        add esi,4
        loop repeta
        
        
        
        
        
        
        mov esi,0
        mov [nr],ebx
        mov ecx,[nr]
        mov edx,0
        
        
        repeta2:
        add dx,word[d+esi]
        add esi,2
        loop repeta2
        
        mov [sum],dx
        mov ax,[sum]
        cwd
        mov bx,[nr]
        idiv bx
        
        mov [cat],ax
        
        
        push dword [cat]
        push dword [nr]
        push dword [sum]
        push dword afisare
        call [printf]
        add esp,4*4
        
        
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
