
/*
*/ # /*
*/ defi\
ne FO\
O 10\
20

#define min(X, Y)  ((X) < (Y) ? (X) : (Y))

#include <stddef.h>
#include <stdio.h>
#include <string.h>

#ifndef __DC__
	#define _xbee_io_debug
#elif defined XBEE_IO_DEBUG
	#define _xbee_io_debug	__debug
#else
	#define _xbee_io_debug	__nodebug
#endif



//EXAMPLE FROM WIKIPEDIA

#if VERBOSE >= 2
//  print("trace message");
#endif

#ifdef __unix__ /* __unix__ is usually defined by compilers targeting Unix systems */
# include <unistd.h>
#elif defined _WIN32 /* _WIN32 is usually defined by compilers targeting 32 or 64 bit Windows systems */
# include <windows.h>
#endif

#if !(defined __LP64__ || defined __LLP64__) || defined _WIN32 && !defined _WIN64
	// we are compiling for a 32-bit system
#else
	// we are compiling for a 64-bit system
#endif

#if RUBY_VERSION == 190
#endif

#define PI 3.14159

#define RADTODEG(x) ((x) * 57.29578)

// debugging macros so we can pin down message origin at a glance
#define WHERESTR  "[file %s, line %d]: "
#define WHEREARG  __FILE__, __LINE__
//...

#line 314 "pi.c"
//printf("line=%d file=%s\n", __LINE__, __FILE__);

#define str(s) #s

//str(p = "foo\n";) // outputs "p = \"foo\\n\";"
//str(\n)           // outputs "\n"

#define xstr(s) str(s)
#define str(s) #s
#define foo 4

//str (foo)  // outputs "foo"
//xstr (foo) // outputs "4"


#pragma message("Do not use ABC, which is deprecated. Use XYZ instead.")

#warning "Do not use ABC, which is deprecated. Use XYZ instead."

#if defined(CREDIT)  
//    credit();  
#elif defined(DEBIT)  
//    debit();  
#else  
//    printerror();  
#endif  

#if DLEVEL > 5  
    #define SIGNAL  1  
    #if STACKUSE == 1  
        #define STACK   200  
    #else  
        #define STACK   100  
    #endif  
#else  
    #define SIGNAL  0  
    #if STACKUSE == 1  
        #define STACK   100  
    #else  
        #define STACK   50  
    #endif  
#endif  
#if DLEVEL == 0  
    #define STACK 0  
#elif DLEVEL == 1  
    #define STACK 100  
#elif DLEVEL > 5  
//    display( debugptr );  
#else  
    #define STACK 200  
#endif  

/*  EXAMPLE.H - Example header file  */  
#if !defined( EXAMPLE_H )  
#define EXAMPLE_H  
  
#endif // !defined( EXAMPLE_H )  

//EXAMPLE FROM WIKIPEDIA
