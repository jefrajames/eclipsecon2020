package org.jefrajames.graphqldemo.score.entity;

import org.eclipse.microprofile.graphql.Description;

/**
 * Something that can be measured
 * 
 */
@Description("Something that is measurable")
public interface Measurable {
    Long getValue();
}
