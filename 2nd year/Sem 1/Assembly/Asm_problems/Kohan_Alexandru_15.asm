bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                       ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...

;15
; s dd 0fffffff9h, 1234FFD9h,0FFFFFFF2h
; len_s equ ($-s)/4
; d times len_s*2 dw 0    
; text db '%d / %d = %d(cat) si %d(rest)',0
; nr dd 0
; sum dd 0
; cat dd 0
; restul dd 0
; impartitor dd 7
; newline db 'asa',0
; our code starts here
segment code use32 class=code
    start:
        ; ...
    ; mov eax,0
    ; mov ebx,0
    ; mov edx,0
    ; mov ecx,2*len_s
    ; mov esi,0
    ; mov edi,0
    
    
    
    
    
    ; repeta:
    ; mov ax,[s+esi]
    ; cmp ax,0
    ; JL continua
    ; JGE nucontinua
        ; continua:
        ; mov ax,[s+esi]
        ; cwd
        ; idiv word[impartitor]
        ; cmp dx,0
        ; JE adauga
        ; JNE nuadauga
            ; adauga:
                ; mov ax,[s+esi]
                ; mov [d+edi],ax
                ; add edi,2
                ; add ebx,1
                ; jmp final
            ; nuadauga:
            ; jmp final
        ; nucontinua:
        ; jmp final
    ; final:
    ; add esi,2
    ; loop repeta
    
    
    
    ; mov [nr],ebx
    ; mov esi,0
    ; mov ecx,[nr]
    ; mov edx,0
    
    

    
    ; repeta2:
    ; mov ax,[d+esi]
    ; add dx,ax
    ; add esi,2
    ; loop repeta2
    
    
    
    ; mov [sum],dx
    
    ; mov ax,[sum]
    ; cwde
    ; cdq
    ; mov ebx,[nr]
    
    ; idiv ebx
    
    
    ; ;eax-cat edx-restul
    
    ; mov [cat],eax
    ; mov [restul],edx
    ; mov ax,[sum]
    ; cwde
    ; mov [sum],eax

    
    ; push dword[restul]
    ; push dword[cat]
    ; push dword[nr]
    ; push dword[sum]
    ; push dword text
    ; call [printf]
    ; add esp,4*5
    mov eax,0
    mov ebx,0
    mov al,110
    cbw
    mov bl,10
    idiv bl
    
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program
