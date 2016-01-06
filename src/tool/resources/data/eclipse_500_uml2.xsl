<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">


    <xsl:output method="xml"/>
    <xsl:template match="/">
        <xsl:apply-templates/>
    </xsl:template>

    <xsl:template match="/Model">
            <System>
                <xsl:attribute name="name">
                    <xsl:value-of select="@name"/>
                </xsl:attribute>
                <xsl:apply-templates/>
            </System>
    </xsl:template>

    <xsl:template match="packagedElement[@type='uml:Model']">
        <Module>
            <xsl:attribute name="name">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
            <xsl:apply-templates/>
        </Module>

    </xsl:template>
    <xsl:template match="packagedElement[@type='uml:Package']">
        <Layer>
            <xsl:attribute name="name">
                <xsl:value-of select="@name"/>
            </xsl:attribute>
        </Layer>
    </xsl:template>
</xsl:stylesheet>
