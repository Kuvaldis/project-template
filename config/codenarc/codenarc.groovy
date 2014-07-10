ruleset {
    // base
    AssertWithinFinallyBlock
    BigDecimalInstantiation
    BooleanGetBoolean
    BrokenNullCheck
    BrokenOddnessCheck
    ClassForName
    ConstantIfExpression
    ConstantTernaryExpression
    DeadCode
    DuplicateCaseStatement
    DuplicateMapKey
    DuplicateSetValue
    EmptyCatchBlock
    EmptyElseBlock
    EmptyFinallyBlock
    EmptyForStatement
    EmptyIfStatement
    EmptyInstanceInitializer
    EmptyMethod
    EmptyStaticInitializer
    EmptySwitchStatement
    EmptySynchronizedStatement
    EmptyTryBlock
    EmptyWhileStatement
    EqualsAndHashCode
    ExplicitGarbageCollection
    ForLoopShouldBeWhileLoop
    HardCodedWindowsFileSeparator
    HardCodedWindowsRootDirectory
    IntegerGetInteger
    RandomDoubleCoercedToZero
    RemoveAllOnSelf
    ReturnFromFinallyBlock
    ThrowExceptionFromFinallyBlock

    // braces
    ElseBlockBraces
    ForStatementBraces
    IfStatementBraces
    WhileStatementBraces

    // concurrency
    DoubleCheckedLocking
    InconsistentPropertyLocking
    InconsistentPropertySynchronization
    NestedSynchronization
    StaticCalendarField
    StaticConnection
    StaticDateFormatField
    StaticMatcherField
    StaticSimpleDateFormatField
    SynchronizedOnGetClass
    SynchronizedOnBoxedPrimitive
    SynchronizedOnString
    SynchronizedReadObjectMethod
    SynchronizedOnReentrantLock
    SystemRunFinalizersOnExit
    ThisReferenceEscapesConstructor
    ThreadLocalNotStaticFinal
    ThreadYield
    UseOfNotifyMethod
    VolatileArrayField
    VolatileLongOrDoubleField
    WaitOutsideOfWhileLoop

    // convention
    ConfusingTernary
    CouldBeElvis
    HashtableIsObsolete
    IfStatementCouldBeTernary
    InvertedIfElse
    LongLiteralWithLowerCaseL
    ParameterReassignment
    TernaryCouldBeElvis
    VectorIsObsolete

    // design
    AbstractClassWithPublicConstructor
    AbstractClassWithoutAbstractMethod
    BooleanMethodReturnsNull
    BuilderMethodWithSideEffects
    CloneableWithoutClone
    CloseWithoutCloseable
    CompareToWithoutComparable
    FinalClassWithProtectedMember
    ImplementationAsType
    LocaleSetDefault
    PrivateFieldCouldBeFinal
    PublicInstanceField
    ReturnsNullInsteadOfEmptyArray
    ReturnsNullInsteadOfEmptyCollection
    SimpleDateFormatMissingLocale
    StatelessSingleton
    ToStringReturnsNull

    // dry
    DuplicateListLiteral
    DuplicateMapLiteral
    DuplicateNumberLiteral
    DuplicateStringLiteral

    // exceptions
    CatchArrayIndexOutOfBoundsException
    CatchError
    CatchIllegalMonitorStateException
    CatchIndexOutOfBoundsException
    CatchNullPointerException
    CatchRuntimeException
    CatchThrowable
    ConfusingClassNamedException
    ExceptionExtendsError
    ExceptionExtendsThrowable
    ExceptionNotThrown
    MissingNewInThrowStatement
    ReturnNullFromCatchBlock
    SwallowThreadDeath
    ThrowError
    ThrowNullPointerException
    ThrowThrowable

    // formatting
    BlankLineBeforePackage
    BracesForClass
    BracesForForLoop
    BracesForIfElse
    BracesForMethod
    BracesForTryCatchFinally
    ClassJavadoc {
        applyToNonMainClasses = true
    }
    ClosureStatementOnOpeningLineOfMultipleLineClosure
    ConsecutiveBlankLines
    LineLength // default 120
    MissingBlankLineAfterImports
    MissingBlankLineAfterPackage
    SpaceAfterCatch
    SpaceAfterComma
    SpaceAfterClosingBrace
    SpaceAfterFor
    SpaceAfterIf
    SpaceAfterOpeningBrace
    SpaceAfterSemicolon
    SpaceAfterSwitch
    SpaceAfterWhile
    SpaceAroundClosureArrow
    SpaceAroundOperator
    SpaceBeforeClosingBrace
    SpaceBeforeOpeningBrace

    // groovyism
    AssignCollectionSort
    AssignCollectionUnique
    ClosureAsLastMethodParameter
    CollectAllIsDeprecated
    ConfusingMultipleReturns
    ExplicitArrayListInstantiation
    ExplicitCallToAndMethod
    ExplicitCallToCompareToMethod
    ExplicitCallToDivMethod
    ExplicitCallToEqualsMethod
    ExplicitCallToGetAtMethod
    ExplicitCallToLeftShiftMethod
    ExplicitCallToMinusMethod
    ExplicitCallToMultiplyMethod
    ExplicitCallToModMethod
    ExplicitCallToOrMethod
    ExplicitCallToPlusMethod
    ExplicitCallToPowerMethod
    ExplicitCallToRightShiftMethod
    ExplicitCallToXorMethod
    ExplicitHashMapInstantiation
    ExplicitLinkedHashMapInstantiation
    ExplicitHashSetInstantiation
    ExplicitLinkedListInstantiation
    ExplicitStackInstantiation
    ExplicitTreeSetInstantiation
    GetterMethodCouldBeProperty
    GroovyLangImmutable
    GStringAsMapKey
    GStringExpressionWithinString
    UseCollectMany
    UseCollectNested

    // imports
    DuplicateImport
    ImportFromSamePackage
    MisorderedStaticImports {
        comesBefore = false
    }
    UnnecessaryGroovyImport
    UnusedImport

    // jdbc
    DirectConnectionManagement
    JdbcConnectionReference
    JdbcResultSetReference
    JdbcStatementReference

    // logging
    LoggerForDifferentClass
    LoggingSwallowsStacktrace
    LoggerWithWrongModifiers
    MultipleLoggers
    Println
    PrintStackTrace
    SystemErrPrint
    SystemOutPrint

    // naming
    ClassName
    ClassNameSameAsFilename
    ConfusingMethodName
    FieldName
    MethodName
    ObjectOverrideMisspelledMethodName
    PackageName
    ParameterName
    PropertyName
    VariableName

    // size
    ClassSize // default 1000 rows
    CyclomaticComplexity {
        maxMethodComplexity = 10
        maxClassAverageMethodComplexity = 10
    }
    MethodCount // default 30
    MethodSize {
        maxLines = 50
    }
    NestedBlockDepth

    // security
    FileCreateTempFile
    InsecureRandom
    NonFinalPublicField
    NonFinalSubclassOfSensitiveInterface
    PublicFinalizeMethod
    ObjectFinalize
    SystemExit
    UnsafeArrayDeclaration

    // serialization
    EnumCustomSerializationIgnored
    SerialPersistentFields
    SerialVersionUID
    SerializableClassMustDefineSerialVersionUID

    // unnecessary
    AddEmptyString
    ConsecutiveLiteralAppends
    ConsecutiveStringConcatenation
    UnnecessaryBigDecimalInstantiation
    UnnecessaryBigIntegerInstantiation
    UnnecessaryBooleanExpression
    UnnecessaryBooleanInstantiation
    UnnecessaryCallForLastElement
    UnnecessaryCallToSubstring
    UnnecessaryCast
    UnnecessaryCatchBlock
    UnnecessaryCollectCall
    UnnecessaryCollectionCall
    UnnecessaryConstructor
    UnnecessaryDefInFieldDeclaration
    UnnecessaryDefInMethodDeclaration
    UnnecessaryDefInVariableDeclaration
    UnnecessaryDotClass
    UnnecessaryDoubleInstantiation
    UnnecessaryElseStatement
    UnnecessaryFinalOnPrivateMethod
    UnnecessaryFloatInstantiation
    UnnecessaryGetter
    UnnecessaryGString
    UnnecessaryIfStatement
    UnnecessaryInstanceOfCheck
    UnnecessaryInstantiationToGetClass
    UnnecessaryIntegerInstantiation
    UnnecessaryLongInstantiation
    UnnecessaryModOne
    UnnecessaryObjectReferences
    UnnecessaryNullCheck
    UnnecessaryNullCheckBeforeInstanceOf
    UnnecessaryOverridingMethod
    UnnecessaryPackageReference
    UnnecessaryParenthesesForMethodCallWithClosure
    UnnecessaryPublicModifier
    UnnecessaryReturnKeyword
    UnnecessarySelfAssignment
    UnnecessarySemicolon
    UnnecessarySubstring
    UnnecessaryStringInstantiation
    UnnecessaryTernaryExpression
    UnnecessaryTransientModifier
    UnnecessaryToString

    // unused
    UnusedArray
    UnusedObject
    UnusedPrivateField
    UnusedPrivateMethod
    UnusedPrivateMethodParameter
    UnusedMethodParameter
    UnusedVariable
}